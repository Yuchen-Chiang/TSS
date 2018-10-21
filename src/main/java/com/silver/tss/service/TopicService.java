package com.silver.tss.service;

import com.alibaba.fastjson.JSONObject;

public interface TopicService {

    public Boolean isRealGEtMaxSelect(String topicId);

    public JSONObject doSelectTopic(String studentId, String topicId);

    public JSONObject undoSelectTopic(String studentId, String topicId);

    public JSONObject deleteTopic(String topicId);

    public JSONObject updateTopic(String topicId, String data, int type);

    public JSONObject queryTopicList();

    public JSONObject countSelectedByType(String topicType);

    public JSONObject countSelectedByTopic(String topicId);
}
