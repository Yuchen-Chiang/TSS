package com.silver.tss.common;

import com.alibaba.fastjson.JSONObject;

public class Response {

    public static JSONObject response(int code) {
        JSONObject response = new JSONObject();
        response.put("code", code);
        return response;
    }

    public static JSONObject response(int code, long count) {
        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("count", count);
        return response;
    }

    public static JSONObject loginSuccess(int code, String classId) {
        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("classId", classId);
        return response;
    }
}
