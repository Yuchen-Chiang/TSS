package com.silver.tss.web;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.common.Response;
import com.silver.tss.service.StatusService;
import com.silver.tss.service.TopicService;
import com.silver.tss.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程设计题目管理接口
 *
 * @author Yuchen Chiang
 */
@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private StatusService statusService;

    /**
     * 学生选中题目
     * /topic/select/topic?studentId=xx&topicId=xx&classId=xx
     *
     * @param studentId 学号
     * @param topicId 题目ID
     * @return { "code" : 200-成功; 400-失败; 401-学生已选过题; 402-选题人数超上限; 403-不在选课时间范围内 }
     */
    @ApiOperation(value = "选题", notes = "根据提交参数进行选题操作\n" +
        "返回：{ \"code\" : 200-成功; 400-失败; 401-学生已选过题; 402-选题人数超上限; 403-不在选课时间范围内 }")
    @ResponseBody
    @RequestMapping(value = "/select/topic", method = RequestMethod.GET)
    public JSONObject selectTopic(
        @ApiParam(required = true, name = "studentId", value = "学号")
        @RequestParam(required = true)
            String studentId,

        @ApiParam(required = true, name = "topicId", value = "题号")
        @RequestParam(required = true)
            String topicId,

        @ApiParam(required = true, name = "classId", value = "班号")
        @RequestParam(required = true)
            String classId
    ) {

        if (statusService.isStatus0() || statusService.isStatus2()) {
            LOGGER.info("studentId={} select topicId={} failed, cause = 403", studentId, topicId);
            return Response.response(403);
        } else if (userService.isStudentUserHasTopic(studentId)) {
            LOGGER.info("studentId={} select topicId={} failed, cause code = 401", studentId,
                topicId);
            return Response.response(401);
        } else if (topicService.isRealGEtMaxSelect(topicId, classId)) {
            LOGGER.info("studentId={} select topicId={} in classId={} failed, cause code = 402",
                studentId, topicId, classId);
            return Response.response(402);
        } else {
            LOGGER.info("studentId={} select topicId={} success", studentId, topicId);
            return topicService.doSelectTopic(studentId, topicId, classId);
        }
    }

    /**
     * 学生丢弃已选题目
     * /topic/drop/topic?studentId=xx&topicId=xx&&classId=xx
     *
     * @param studentId 学号
     * @param topicId 题目ID
     * @return { "code" : 200-成功; 400-失败; 401-该学生未选择本题目; 402-不在选课时间范围内 }
     */
    @ApiOperation(value = "放弃题目", notes = "根据提交参数进行放弃题目操作\n" +
        "返回：{ \"code\" : 200-成功; 400-失败; 401-该学生未选择本题目; 402-不在选课时间范围内 }")
    @ResponseBody
    @RequestMapping(value = "/drop/topic", method = RequestMethod.GET)
    public JSONObject dropTopic(
        @ApiParam(required = true, name = "studentId", value = "学号")
        @RequestParam(required = true)
            String studentId,

        @ApiParam(required = true, name = "topicId", value = "题号")
        @RequestParam(required = true)
            String topicId,

        @ApiParam(required = true, name = "classId", value = "班号")
        @RequestParam(required = true)
            String classId
    ) {

        if (statusService.isStatus0() || statusService.isStatus2()) {
            LOGGER.info("studentId={} drop topicId={} failed, cause = 402", studentId, topicId);
            return Response.response(402);
        } else if (userService.isStudentUserHasTopic(studentId)) {
            LOGGER.info("studentId={} drop topicId={} failed, cause code = 401", studentId, topicId);
            return Response.response(401);
        } else {
            LOGGER.info("studentId={} drop topicId={} success", studentId, topicId);
            return topicService.undoSelectTopic(studentId, topicId, classId);
        }
    }

    /**
     * 删除题目
     * /topic/delete/topic?topicId=xx
     *
     * @param topicId 题目ID
     * @return { "code" : 200-成功; 400-失败 }
     */
    @ApiOperation(value = "删除题目", notes = "根据提交参数进行删除题目操作\n" +
        "返回：{ \"code\" : 200-成功; 400-失败 }")
    @ResponseBody
    @RequestMapping(value = "/delete/topic", method = RequestMethod.GET)
    public JSONObject deleteTopic(
        @ApiParam(required = true, name = "topicId", value = "题号")
        @RequestParam(required = true)
            String topicId
    ) {
        LOGGER.info("topicId={} is deleted", topicId);
        return topicService.deleteTopic(topicId);
    }

    /**
     * 更新题目
     * /topic/update/topic?topicId=xx&data=xx&type=xx
     *
     * @param topicId 题目ID
     * @param data 待更新数据
     * @param type 更新类型, 0-题目名称; 1-题目描述; 2-题目人数上限
     * @return { "code" : 200-成功; 400-失败; 401-参数错误; 402-题目人数上限小于实际选题人数 }
     */
    @ApiOperation(value = "更新题目", notes = "根据提交参数进行更新题目操作\n" +
        "返回：{ \"code\" : 200-成功; 400-失败; 401-参数错误; 402-题目人数上限小于实际选题人数 }")
    @ResponseBody
    @RequestMapping(value = "/update/topic", method = RequestMethod.GET)
    public JSONObject updateTopic(
        @ApiParam(required = true, name = "topicId", value = "题号")
        @RequestParam(required = true)
            String topicId,

        @ApiParam(required = true, name = "data", value = "待更新数据")
        @RequestParam(required = true)
            String data,

        @ApiParam(required = true, name = "type", value = "更新类型, 0-题目名称; 1-题目描述; 2-题目人数上限")
        @RequestParam(required = true)
            String type) {
        return topicService.updateTopic(topicId, data, Integer.parseInt(type));
    }


    /**
     * 获取题目列表
     * /topic/get/list
     *
     * @return { "code" : 200-成功; 400-失败 "size" : 1 "list" : [ { "id" : "xxx", "topicId" : "xxx",
     * "topicName" : "xxx", "topicType" : "xxx", "topicDescription" : "xxx", "topicMaxSelected" :
     * "xxx", "topicRealSelected1" : "xxx", "topicRealSelected2" : "xxx", "topicRealSelected3" :
     * "xxx", "yn" : "true", "createTime" : "xxx", "modifiedTime" : "xxx" } ] }
     */
    @ApiOperation(value = "获取题目列表", notes = "获取题目列表\n" +
        "返回：{ \"code\" : 200-成功; 400-失败 \"size\" : 1 \"list\" : [ { \"id\" : \"xxx\", \"topicId\" : \"xxx\",\n"
        + "\"topicName\" : \"xxx\", \"topicType\" : \"xxx\", \"topicDescription\" : \"xxx\", \"topicMaxSelected\" :\n"
        + "\"xxx\", \"topicRealSelected1\" : \"xxx\", \"topicRealSelected2\" : \"xxx\", \"topicRealSelected3\" :\n"
        + "\"xxx\", \"yn\" : \"true\", \"createTime\" : \"xxx\", \"modifiedTime\" : \"xxx\" } ] }")
    @ResponseBody
    @RequestMapping(value = "/get/list", method = RequestMethod.GET)
    public JSONObject getTopicsList() {
        LOGGER.info("query topic info list");
        return topicService.queryTopicList();
    }
}
