package com.silver.tss.common;

import com.alibaba.fastjson.JSONObject;

public class Response {

    public static JSONObject response(int code) {
        JSONObject response = new JSONObject();
        response.put("code", code);
        return response;
    }
}
