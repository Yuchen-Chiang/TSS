package com.silver.tss.service;

import com.alibaba.fastjson.JSONObject;

public interface StatusService {

    public Boolean isStatus0();

    public Boolean isStatus1();

    public Boolean isStatus2();

    public JSONObject updateStatus(int status);
}
