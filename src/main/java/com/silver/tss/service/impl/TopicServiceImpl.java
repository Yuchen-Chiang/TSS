package com.silver.tss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.dao.StudentMapper;
import com.silver.tss.dao.TopicMapper;
import com.silver.tss.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("TopicService")
public class TopicServiceImpl implements TopicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Boolean isRealGtMaxSelect(String topicId) {
        return null;
    }

    @Override
    public JSONObject doSelectTopic(String studentId, String topicId) {
        return null;
    }

    @Override
    public JSONObject undoSelectTopic(String studentId, String topicId) {
        return null;
    }

    @Override
    public JSONObject deleteTopic(String topicId) {
        return null;
    }

    @Override
    public JSONObject updateTopic(String topicId, String data, String type) {
        return null;
    }

    @Override
    public JSONObject queryTopicList(int offset, int limit) {
        return null;
    }

    @Override
    public JSONObject countSelectedByType(String topicType) {
        return null;
    }

    @Override
    public JSONObject countSelectedByTopic(String topicId) {
        return null;
    }
}
