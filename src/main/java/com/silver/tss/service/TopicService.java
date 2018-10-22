package com.silver.tss.service;

import com.alibaba.fastjson.JSONObject;

public interface TopicService {

    Boolean isRealGEtMaxSelect(String topicId, String classId);

    JSONObject doSelectTopic(String studentId, String topicId, String classId);

    JSONObject undoSelectTopic(String studentId, String topicId, String classId);

    JSONObject deleteTopic(String topicId);

    JSONObject updateTopic(String topicId, String data, int type);

    JSONObject queryTopicList();

    JSONObject countSelectedByType(String topicType);

    JSONObject countSelectedByTopic(String topicId);
}
