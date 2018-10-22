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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("TopicService")
public class TopicServiceImpl implements TopicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Value("${tss.class-id.1}")
    private String classId1;

    @Value("${tss.class-id.2}")
    private String classId2;

    @Value("${tss.class-id.3}")
    private String classId3;

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Boolean isRealGEtMaxSelect(String topicId, String classId) {
        TopicExample te = new TopicExample();
        te.createCriteria()
                .andTopicIdEqualTo(topicId)
                .andYnEqualTo(true);
        List<Topic> topics = topicMapper.selectByExample(te);
        if (topics.size() > 0) {
            Topic topic = topics.get(0);
            if (classId1.equals(classId)) {
                return topic.getTopicRealSelected1() >= topic.getTopicMaxSelected();
            } else if (classId2.equals(classId)) {
                return topic.getTopicRealSelected2() >= topic.getTopicMaxSelected();
            } else if (classId3.equals(classId)) {
                return topic.getTopicRealSelected3() >= topic.getTopicMaxSelected();
            }
        }
        return false;
    }

    @Override
    @Transactional
    public JSONObject doSelectTopic(String studentId, String topicId, String classId) {
        TopicExample te = new TopicExample();
        te.createCriteria()
                .andTopicIdEqualTo(topicId)
                .andYnEqualTo(true);
        List<Topic> topics = topicMapper.selectByExample(te);
        Topic topic;
        if (topics.size() > 0) topic = topics.get(0);
        else return Response.response(400);

        Topic t = new Topic();

        if (classId1.equals(classId)) {
            t.setTopicRealSelected1(topic.getTopicRealSelected1() + 1);
        } else if (classId2.equals(classId)) {
            t.setTopicRealSelected2(topic.getTopicRealSelected2() + 1);
        } else if (classId3.equals(classId)) {
            t.setTopicRealSelected3(topic.getTopicRealSelected3() + 1);
        }

        Student s = new Student();
        s.setTopicId(topicId);
        s.setTopicName(topic.getTopicName());
        StudentExample se = new StudentExample();
        se.createCriteria()
                .andStudentIdEqualTo(studentId)
                .andYnEqualTo(true);

        return topicMapper.updateByExampleSelective(t, te) > 0 && studentMapper.updateByExampleSelective(s, se) > 0
                ? Response.response(200) : Response.response(400);
    }

    @Override
    @Transactional
    public JSONObject undoSelectTopic(String studentId, String topicId, String classId) {
        TopicExample te = new TopicExample();
        te.createCriteria()
                .andTopicIdEqualTo(topicId)
                .andYnEqualTo(true);
        List<Topic> topics = topicMapper.selectByExample(te);
        Topic topic;
        if (topics.size() > 0) topic = topics.get(0);
        else return Response.response(400);

        Topic t = new Topic();

        if (classId1.equals(classId)) {
            t.setTopicRealSelected1(topic.getTopicRealSelected1() - 1);
        } else if (classId2.equals(classId)) {
            t.setTopicRealSelected2(topic.getTopicRealSelected2() - 1);
        } else if (classId3.equals(classId)) {
            t.setTopicRealSelected3(topic.getTopicRealSelected3() - 1);
        }

        Student s = new Student();
        s.setTopicId("null");
        s.setTopicName("null");
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
        s.setTopicName("null");
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
                if (Integer.valueOf(data) < Math.min(topic.getTopicRealSelected1(), Math.min(topic.getTopicRealSelected2(), topic.getTopicRealSelected3())) ) return Response.response(402);
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
        for (Topic topic : topics) count += (topic.getTopicRealSelected1() + topic.getTopicRealSelected2() + topic.getTopicRealSelected3());
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
        for (Topic topic : topics) count += (topic.getTopicRealSelected1() + topic.getTopicRealSelected2() + topic.getTopicRealSelected3());
        return Response.response(200, count);
    }
}
