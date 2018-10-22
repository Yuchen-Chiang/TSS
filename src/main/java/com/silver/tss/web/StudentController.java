package com.silver.tss.web;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.common.Response;
import com.silver.tss.service.StatusService;
import com.silver.tss.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生账户管理接口
 *
 * @author Yuchen Chiang
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    /**
     * 学生账户登录
     * /student/login?studentId=xx&studentPwd=xx
     *
     * @param studentId 学号
     * @param studentPwd 密码
     * @return
     * {
     *     "code" : 200-成功; 300-需更新密码; 400-失败
     *     "classId" : "xxx"
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public JSONObject login(String studentId, String studentPwd) {
        LOGGER.info("studentId={} with studentPwd={} login tss", studentId, studentPwd);
        if (userService.isUserExist(studentId, studentPwd)) {
            if (userService.isUserChangePwd(studentId)) {
                String classId = userService.queryStudentClassId(studentId);
                if (classId != null) return Response.loginSuccess(200, classId);
                else return Response.response(400);
            } else return Response.response(300);
        } else return Response.response(400);
    }

    /**
     * 学生账户密码更改
     * /student/update/pwd?studentId=xx&studentPwd=xx
     *
     * @param studentId 学号
     * @param studentPwd 密码
     * @return
     * {
     *     "code" : 200-成功; 400-失败
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/update/pwd", method = RequestMethod.GET)
    public JSONObject updatePwd(String studentId, String studentPwd) {
        LOGGER.info("studentId={} is trying to change tss pwd={}", studentId, studentPwd);
        if (statusService.isStatus1() || statusService.isStatus2()) return Response.response(400);
        return userService.updateUserInfo(studentId, studentPwd);
    }

    /**
     * 按班级获取学生账户列表
     * /student/get/list?classId=xx
     *
     * @param classId 班级号 1-1班; 2-2班; 3-3班; -1-全部班级
     * @return
     * {
     *     "code" : 200-成功; 400-失败
     *     "size" : 1
     *     "list" : [
     *          {
     *              "id" : "xxx",
     *              "studentId" : "xxx",
     *              "studentName" : "xxx",
     *              "classId" : "xxx",
     *              "topicId" : "xxx",
     *              "topicName" : "xxx",
     *              "yn" : "true",
     *              "createTime" : "xxx",
     *              "modifiedTime" : "xxx"
     *          }
     *     ]
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/get/list", method = RequestMethod.GET)
    public JSONObject getStudentsList(String classId) {
        LOGGER.info("query student info list with classId={}", classId);
        return userService.queryStudentUserList(classId);
    }

}
