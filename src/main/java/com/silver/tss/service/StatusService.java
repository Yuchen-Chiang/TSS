package com.silver.tss.service;

import com.alibaba.fastjson.JSONObject;

public interface StatusService {

    Boolean isStatus0();

    Boolean isStatus1();

    Boolean isStatus2();

    Boolean isStatus3();

    JSONObject updateStatus(int status);
}
