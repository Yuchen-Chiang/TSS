package com.silver.tss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.dao.StudentMapper;
import com.silver.tss.dao.UserMapper;
import com.silver.tss.domain.User;
import com.silver.tss.domain.UserExample;
import com.silver.tss.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Boolean isUserChangePwd(String studentId) {
        UserExample ue = new UserExample();
        ue.createCriteria()
                .andStudentIdEqualTo(studentId)
                .andYnEqualTo(true);
        List<User> users = userMapper.selectByExample(ue);
        return users.size() > 0 ? users.get(0).getStudentStatus() : false;
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
