package com.silver.tss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.service.TopicService;
import org.springframework.stereotype.Service;

@Service("TopicService")
public class TopicServiceImpl implements TopicService {

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
