package com.silver.tss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.dao.StudentMapper;
import com.silver.tss.dao.UserMapper;
import com.silver.tss.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Boolean isUserChangePwd(String studentId) {
        return null;
    }

    @Override
    public Boolean isStudentUserHasTopic(String studentId) {
        return null;
    }

    @Override
    public JSONObject isUserExist(String studentId, String studentPwd) {
        return null;
    }

    @Override
    public JSONObject queryStudentUserList(int offset, int limit) {
        return null;
    }

    @Override
    public JSONObject updateUserInfo(String studentId, String studentPwd) {
        return null;
    }

    @Override
    public JSONObject countSelectedByClass(String classId) {
        return null;
    }

    @Override
    public JSONObject countSelected() {
        return null;
    }
}
