package com.silver.tss.web;

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
    public String importStudents(MultipartFile file) {

        return null;
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
    public String importTopics(MultipartFile file) {

        return null;
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
    public String importTeachers(MultipartFile file) {

        return null;
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
    public ResponseEntity exportStudents(String classId) {

        return null;
    }
}
