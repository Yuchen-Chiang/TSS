package com.silver.tss.web;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.common.Response;
import com.silver.tss.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * EXCEL导入导出接口
 *
 * @author Yuchen Chiang
 */
@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private ExcelService excelService;

    /**
     * 导入学生EXCEL
     * [POST] /excel/import/students
     *
     * @param file excel
     * @return
     * {
     *     "code" : 200-成功; 400-失败
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/import/students", method = RequestMethod.POST)
    public JSONObject importStudents(MultipartFile file) {
        LOGGER.info("load students file {}", file.getName());
        return file.getSize() <= 0 ? Response.response(400) : excelService.importStudentsExcel(file.getOriginalFilename(), file);
    }

    /**
     * 导入题目EXCEL
     * [POST] /excel/import/topics
     *
     * @param file excel
     * @return
     * {
     *     "code" : 200-成功; 400-失败
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/import/topics", method = RequestMethod.POST)
    public JSONObject importTopics(MultipartFile file) {
        LOGGER.info("load topics file {}", file.getName());
        return file.getSize() <= 0 ? Response.response(400) : excelService.importTopicsExcel(file.getOriginalFilename(), file);
    }

    /**
     * 导入教师EXCEL
     * [POST] /excel/import/teachers
     *
     * @param file excel
     * @return
     * {
     *     "code" : 200-成功; 400-失败
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/import/teachers", method = RequestMethod.POST)
    public JSONObject importTeachers(MultipartFile file) {
        LOGGER.info("load teachers file {}", file.getName());
        return file.getSize() <= 0 ? Response.response(400) : excelService.importTeachersExcel(file.getOriginalFilename(), file);
    }

    /**
     * 导出学生选题EXCEL
     * [GET] /excel/export/students?classId=1
     *
     * @param classId 班级号; classId=-1 导出全部选题信息表
     * @return excel
     */
    @ResponseBody
    @RequestMapping(value = "/export/students", method = RequestMethod.GET)
    public ResponseEntity<String> exportStudents(String classId) {
        return new ResponseEntity<>("暂不提供该服务", HttpStatus.OK);
    }
}
