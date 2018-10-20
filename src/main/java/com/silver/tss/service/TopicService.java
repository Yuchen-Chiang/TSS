package com.silver.tss.service;

import com.alibaba.fastjson.JSONObject;

public interface TopicService {

    public Boolean isRealGtMaxSelect(String topicId);

    public JSONObject doSelectTopic(String studentId, String topicId);

    public JSONObject undoSelectTopic(String studentId, String topicId);

    public JSONObject deleteTopic(String topicId);

    public JSONObject updateTopic(String topicId, String data, String type);

    public JSONObject queryTopicList(int offset, int limit);

    public JSONObject countSelectedByType(String topicType);

    public JSONObject countSelectedByTopic(String topicId);
}
