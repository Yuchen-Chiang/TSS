package com.silver.tss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.common.Response;
import com.silver.tss.dao.StatusMapper;
import com.silver.tss.domain.Status;
import com.silver.tss.domain.StatusExample;
import com.silver.tss.service.StatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("statusService")
public class StatusServiceImpl implements StatusService {

    @Resource
    private StatusMapper statusMapper;

    @Override
    public Boolean isStatus0() {
        List<Status> statuses = getStatusList();
        return statuses.size() > 0 && statuses.get(0).getTssStatus() == 0;
    }

    @Override
    public Boolean isStatus1() {
        List<Status> statuses = getStatusList();
        return statuses.size() > 0 && statuses.get(0).getTssStatus() == 1;
    }

    @Override
    public Boolean isStatus2() {
        List<Status> statuses = getStatusList();
        return statuses.size() > 0 && statuses.get(0).getTssStatus() == 2;
    }

    @Override
    public JSONObject updateStatus(int status) {
        Status s = new Status();
        s.setTssStatus(status);
        StatusExample se = new StatusExample();
        se.createCriteria().andIdEqualTo(1);
        return statusMapper.updateByExampleSelective(s, se) > 0 ? Response.response(200) : Response.response(400);
    }

    private List<Status> getStatusList() {
        StatusExample se = new StatusExample();
        se.createCriteria().andIdEqualTo(1);
        return statusMapper.selectByExample(se);
    }
}
