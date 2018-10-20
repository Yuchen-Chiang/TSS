package com.silver.tss.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * 学生账户登录
     * /student/login?student=xx&studentPwd=xx
     *
     * @param studentId 学号
     * @param studentPwd 密码
     * @return
     * {
     *     "code" : 200-成功; 300-需更新密码; 400-失败
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String studentId, String studentPwd) {

        return null;
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
    public String updatePwd(String studentId, String studentPwd) {

        return null;
    }

    /**
     * 服务端分页获取学生账户列表
     * /student/get/list?offset=xx&limit=xx
     *
     * @param offset 偏移量
     * @param limit 单次上限
     * @return
     * {
     *     "code" : 200-成功; 400-失败; 401-空
     *     "size" : 1
     *     "list" : [
     *          {
     *              "id" : "xxx",
     *              "studentId" : "xxx",
     *              "studentName" : "xxx",
     *              "studentStatus" : "xxx",
     *              "classId" : "xxx",
     *              "topicId" : "xxx",
     *              "yn" : "1",
     *              "createTime" : "xxx",
     *              "modifiedTime" : "xxx"
     *          }
     *     ]
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/get/list", method = RequestMethod.GET)
    public String getStudentsList(int offset, int limit) {

        return null;
    }

}
