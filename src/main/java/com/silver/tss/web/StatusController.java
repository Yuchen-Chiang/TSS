package com.silver.tss.web;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.common.Response;
import com.silver.tss.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 状态管理器
 *
 * @author Yuchen Chiang
 */
@RestController
@RequestMapping(value = "/status")
public class StatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    private StatusService statusService;

    /**
     * 改变系统状态
     * /status/change/status?status=xx
     *
     * @param status 0-change pwd; 1-start select; 2-end select
     * @return
     * {
     *     "code" : 200-成功; 400-失败
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/change/status", method = RequestMethod.GET)
    public JSONObject changeStatus(int status) {
        LOGGER.info("change status={}", status);
        return status == 0 || status == 1 || status == 2 || status == 3
                ? statusService.updateStatus(status)
                : Response.response(400);
    }
}
