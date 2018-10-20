package com.silver.tss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.dao.StatusMapper;
import com.silver.tss.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("statusService")
public class StatusServiceImpl implements StatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusServiceImpl.class);

    @Resource
    private StatusMapper statusMapper;

    @Override
    public Boolean isStatus0() {
        return null;
    }

    @Override
    public Boolean isStatus1() {
        return null;
    }

    @Override
    public Boolean isStatus2() {
        return null;
    }

    @Override
    public JSONObject updateStatus(int status) {
        return null;
    }
}
