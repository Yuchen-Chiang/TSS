package com.silver.tss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.common.Response;
import com.silver.tss.dao.StudentMapper;
import com.silver.tss.dao.UserMapper;
import com.silver.tss.domain.Student;
import com.silver.tss.domain.StudentExample;
import com.silver.tss.domain.User;
import com.silver.tss.domain.UserExample;
import com.silver.tss.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

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
        StudentExample se = new StudentExample();
        se.createCriteria()
            .andStudentIdEqualTo(studentId)
            .andYnEqualTo(true);
        List<Student> students = studentMapper.selectByExample(se);

        if (students == null || students.isEmpty()) {
            return Boolean.FALSE;
        }
        return Optional.ofNullable(students.get(0))
            .map(s -> !"null".equals(s.getTopicId()))
            .orElse(false);
    }

    @Override
    public Boolean isUserExist(String studentId, String studentPwd) {
        UserExample ue = new UserExample();
        ue.createCriteria()
            .andStudentIdEqualTo(studentId)
            .andStudentPwdEqualTo(studentPwd)
            .andYnEqualTo(true);
        return userMapper.selectByExample(ue).size() > 0;
    }

    @Override
    public Student queryStudentInfo(String studentId) {
        StudentExample se = new StudentExample();
        se.createCriteria()
            .andStudentIdEqualTo(studentId)
            .andYnEqualTo(true);
        List<Student> students = studentMapper.selectByExample(se);
        return students.size() > 0 ? students.get(0) : null;
    }

    @Override
    public JSONObject queryStudentUserList(String classId) {
        StudentExample se = new StudentExample();
        se.createCriteria()
            .andClassIdEqualTo(classId)
            .andYnEqualTo(true);
        List<Student> students = studentMapper.selectByExample(se);
        JSONObject response = new JSONObject();
        response.put("code", 200);
        response.put("size", students.size());
        response.put("list", students);
        return response;
    }

    @Override
    public Boolean updateUserInfo(String studentId, String studentPwd) {
        User user = new User();
        user.setStudentStatus(true);
        user.setStudentPwd(studentPwd);
        user.setModifyTime(new Date());
        UserExample ue = new UserExample();
        ue.createCriteria()
            .andStudentIdEqualTo(studentId)
            .andYnEqualTo(true);
        return userMapper.updateByExampleSelective(user, ue) > 0;
    }

    @Override
    public JSONObject countSelectedByClass(String classId) {
        StudentExample se = new StudentExample();
        se.createCriteria()
            .andClassIdEqualTo(classId)
            //todo null的问题
            .andTopicIdNotEqualTo("null")
            .andYnEqualTo(true);
        long count = studentMapper.countByExample(se);
        return Response.response(200, count);
    }

    @Override
    public JSONObject countSelected() {
        StudentExample se = new StudentExample();
        se.createCriteria()
            //todo null的问题
            .andTopicIdNotEqualTo("null")
            .andYnEqualTo(true);
        long count = studentMapper.countByExample(se);
        return Response.response(200, count);
    }
}
