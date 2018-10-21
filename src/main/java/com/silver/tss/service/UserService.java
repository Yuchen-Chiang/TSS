package com.silver.tss.service;

import com.alibaba.fastjson.JSONObject;

public interface UserService {

    public Boolean isUserChangePwd(String studentId);

    public Boolean isStudentUserHasTopic(String studentId);

    public JSONObject isUserExist(String studentId, String studentPwd);

    public JSONObject queryStudentUserList(String classId);

    public JSONObject updateUserInfo(String studentId, String studentPwd);

    public JSONObject countSelectedByClass(String classId);

    public JSONObject countSelected();
}
