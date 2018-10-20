package com.silver.tss.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * 学生选中题目
     * /topic/select/topic?studentId=xx&topicId=xx
     *
     * @param studentId 学号
     * @param topicId 题目ID
     * @return
     * {
     *     "code" : 200-成功; 400-失败; 401-不存在该题目; 402-学号不存在; 403-选题人数超上限
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/select/topic", method = RequestMethod.GET)
    public String selectTopic(String studentId, String topicId) {

        return null;
    }

    /**
     * 学生丢弃已选题目
     * /topic/drop/topic?studentId=xx&topicId=xx
     *
     * @param studentId 学号
     * @param topicId 题目ID
     * @return
     * {
     *     "code" : 200-成功; 400-失败; 401-不存在该题目; 402-学号不存在
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/drop/topic", method = RequestMethod.GET)
    public String dropTopic(String studentId, String topicId) {

        return null;
    }

    /**
     * 删除题目
     * /topic/delete/topic?topicId=xx
     *
     * @param topicId 题目ID
     * @return
     * {
     *     "code" : 200-成功; 400-失败; 401-不存在该题目
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/delete/topic", method = RequestMethod.GET)
    public String deleteTopic(String topicId) {

        return null;
    }

    /**
     * 更新题目
     * /topic/update/topic?topicId=xx&data=xx&type=xx
     *
     * @param topicId 题目ID
     * @param data 待更新数据
     * @param type 更新类型, 0-题目名称; 1-题目描述; 2-题目人数上限
     * @return
     * {
     *     "code" : 200-成功; 400-失败; 401-不存在该题目; 402-参数错误; 403-题目人数上限小于实际选题人数
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/update/topic", method = RequestMethod.GET)
    public String deleteTopic(String topicId, String data, String type) {

        return null;
    }



    /**
     * 服务端分页获取题目列表
     * /topic/get/list?offset=xx&limit=xx
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
     *              "topicId" : "xxx",
     *              "topicName" : "xxx",
     *              "topicType" : "xxx",
     *              "topicDescription" : "xxx",
     *              "topicMaxSelected" : "xxx",
     *              "topicRealSelected" : "xxx",
     *              "yn" : "1",
     *              "createTime" : "xxx",
     *              "modifiedTime" : "xxx"
     *          }
     *     ]
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/get/list", method = RequestMethod.GET)
    public String getTopicsList(int offset, int limit) {

        return null;
    }
}
