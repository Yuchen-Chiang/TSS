package com.silver.tss.service;

import com.alibaba.fastjson.JSONObject;

public interface UserService {

    Boolean isUserChangePwd(String studentId);

    Boolean isStudentUserHasTopic(String studentId);

    JSONObject isUserExist(String studentId, String studentPwd);

    JSONObject queryStudentUserList(String classId);

    JSONObject updateUserInfo(String studentId, String studentPwd);

    JSONObject countSelectedByClass(String classId);

    JSONObject countSelected();
}
