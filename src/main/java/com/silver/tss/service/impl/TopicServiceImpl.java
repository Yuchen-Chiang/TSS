package com.silver.tss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.common.Response;
import com.silver.tss.dao.StudentMapper;
import com.silver.tss.dao.TopicMapper;
import com.silver.tss.domain.Student;
import com.silver.tss.domain.StudentExample;
import com.silver.tss.domain.Topic;
import com.silver.tss.domain.TopicExample;
import com.silver.tss.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("TopicService")
public class TopicServiceImpl implements TopicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Boolean isRealGEtMaxSelect(String topicId) {
        TopicExample te = new TopicExample();
        te.createCriteria()
                .andTopicIdEqualTo(topicId)
                .andYnEqualTo(true);
        List<Topic> topics = topicMapper.selectByExample(te);
        return Optional.ofNullable(topics.get(0))
                .map(t -> t.getTopicRealSelected() >= t.getTopicMaxSelected())
                .orElse(false);
    }

    @Override
    @Transactional
    public JSONObject doSelectTopic(String studentId, String topicId) {
        TopicExample te = new TopicExample();
        te.createCriteria()
                .andTopicIdEqualTo(topicId)
                .andYnEqualTo(true);
        List<Topic> topics = topicMapper.selectByExample(te);
        Topic topic;
        if (topics.size() > 0) topic = topics.get(0);
        else return Response.response(400);

        Topic t = new Topic();
        t.setTopicRealSelected(topic.getTopicRealSelected() + 1);

        Student s = new Student();
        s.setTopicId(topicId);
        StudentExample se = new StudentExample();
        se.createCriteria()
                .andStudentIdEqualTo(studentId)
                .andYnEqualTo(true);

        return topicMapper.updateByExampleSelective(t, te) > 0 && studentMapper.updateByExampleSelective(s, se) > 0
                ? Response.response(200) : Response.response(400);
    }

    @Override
    @Transactional
    public JSONObject undoSelectTopic(String studentId, String topicId) {
        TopicExample te = new TopicExample();
        te.createCriteria()
                .andTopicIdEqualTo(topicId)
                .andYnEqualTo(true);
        List<Topic> topics = topicMapper.selectByExample(te);
        Topic topic;
        if (topics.size() > 0) topic = topics.get(0);
        else return Response.response(400);

        Topic t = new Topic();
        t.setTopicRealSelected(topic.getTopicRealSelected() - 1);

        Student s = new Student();
        s.setTopicId("null");
        StudentExample se = new StudentExample();
        se.createCriteria()
                .andStudentIdEqualTo(studentId)
                .andYnEqualTo(true);

        return topicMapper.updateByExampleSelective(t, te) > 0 && studentMapper.updateByExampleSelective(s, se) > 0
                ? Response.response(200) : Response.response(400);
    }

    @Override
    @Transactional
    public JSONObject deleteTopic(String topicId) {
        Topic t = new Topic();
        t.setYn(false);
        t.setCreateTime(new Date());
        TopicExample te = new TopicExample();
        te.createCriteria()
                .andTopicIdEqualTo(topicId)
                .andYnEqualTo(true);

        StudentExample se = new StudentExample();
        se.createCriteria()
                .andTopicIdEqualTo(topicId)
                .andYnEqualTo(true);
        Student s = new Student();
        //todo 可能有点问题，需测试传入null
        s.setTopicId("null");
        return studentMapper.updateByExampleSelective(s, se) > 0 && topicMapper.updateByExampleSelective(t, te) > 0 ? Response.response(200) : Response.response(400);
    }

    @Override
    @Transactional
    public JSONObject updateTopic(String topicId, String data, int type) {
        TopicExample te = new TopicExample();
        te.createCriteria()
                .andTopicIdEqualTo(topicId)
                .andYnEqualTo(true);
        Topic t = new Topic();
        if (type == 0) t.setTopicName(data);
        else if (type == 1) t.setTopicDescription(data);
        else if (type == 2) {
            List<Topic> topics = topicMapper.selectByExample(te);
            if (topics.size() > 0) {
                Topic topic = topics.get(0);
                if (Integer.valueOf(data) < topic.getTopicRealSelected()) return Response.response(402);
                else t.setTopicMaxSelected(Integer.valueOf(data));
            } else return Response.response(401);
        }
        else return Response.response(401);
        t.setModifyTime(new Date());
        return topicMapper.updateByExample(t, te) > 0 ? Response.response(200) : Response.response(400);
    }

    @Override
    public JSONObject queryTopicList() {
        TopicExample te = new TopicExample();
        te.createCriteria().andYnEqualTo(true);
        List<Topic> topics = topicMapper.selectByExample(te);
        JSONObject response = new JSONObject();
        response.put("code", 200);
        response.put("size", topics.size());
        response.put("list", topics);
        return response;
    }

    @Override
    public JSONObject countSelectedByType(String topicType) {
        TopicExample te = new TopicExample();
        te.createCriteria()
                .andTopicTypeEqualTo(topicType)
                .andYnEqualTo(true);
        List<Topic> topics = topicMapper.selectByExample(te);
        long count = 0;
        for (Topic topic : topics) count += topic.getTopicRealSelected();
        return Response.response(200, count);
    }

    @Override
    public JSONObject countSelectedByTopic(String topicId) {
        TopicExample te = new TopicExample();
        te.createCriteria()
                .andTopicIdEqualTo(topicId)
                .andYnEqualTo(true);
        List<Topic> topics = topicMapper.selectByExample(te);
        int count = 0;
        for (Topic topic : topics) count += topic.getTopicRealSelected();
        return Response.response(200, count);
    }
}
