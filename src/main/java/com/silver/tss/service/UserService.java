package com.silver.tss.service;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.domain.Student;

public interface UserService {

    Boolean isUserChangePwd(String studentId);

    Boolean isStudentUserHasTopic(String studentId);

    Boolean isUserExist(String studentId, String studentPwd);

    Student queryStudentInfo(String studentId);

    JSONObject queryStudentUserList(String classId);

    JSONObject updateUserInfo(String studentId, String studentPwd);

    JSONObject countSelectedByClass(String classId);

    JSONObject countSelected();
}
