package com.silver.tss.web;

import com.silver.tss.service.TopicService;
import com.silver.tss.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计功能接口
 *
 * @author Yuchen Chiang
 */
@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    /**
     * 按题目类型统计选择人数
     * /statistics/get/type?topicType=xx
     *
     * @param topicType 题目类型
     * @return
     * {
     *     "code" : 200-成功; 400-失败
     *     "count" : xxx
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/get/type", method = RequestMethod.GET)
    public String getTypeInfo(String topicType) {

        return null;
    }

    /**
     * 按题目统计选择人数
     * /statistics/get/topic?topicId=xx
     *
     * @param topicId 题目ID
     * @return
     * {
     *     "code" : 200-成功; 400-失败
     *     "count" : xxx
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/get/topic", method = RequestMethod.GET)
    public String getTopicInfo(String topicId) {

        return null;
    }

    /**
     * 按班级统计选择人数
     * /statistics/get/class?classId=xx
     *
     * @param classId 班级ID
     * @return
     * {
     *     "code" : 200-成功; 400-失败
     *     "count" : xxx
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/get/class", method = RequestMethod.GET)
    public String getClassInfo(String classId) {

        return null;
    }

    /**
     * 统计全局选题人数
     * /statistics/get/final
     *
     * @return
     * {
     *     "code" : 200-成功; 400-失败
     *     "count" : xxx
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/get/final", method = RequestMethod.GET)
    public String getFinalInfo() {

        return null;
    }
}
