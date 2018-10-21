package com.silver.tss.web;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.common.Response;
import com.silver.tss.service.ExcelService;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

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
    public JSONObject importStudents(@RequestParam("file") MultipartFile file) {
        LOGGER.info("load students file {}", file.getOriginalFilename());
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
    public JSONObject importTopics(@RequestParam("file") MultipartFile file) {
        LOGGER.info("load topics file {}", file.getOriginalFilename());
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
    public JSONObject importTeachers(@RequestParam("file") MultipartFile file) {
        LOGGER.info("load teachers file {}", file.getOriginalFilename());
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
    public void exportStudents(String classId, HttpServletResponse response) {
        LOGGER.info("export student class file with classId={}", classId);
        Workbook wb =  excelService.exportStudentsExcel(classId);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=export_"+ classId +".xls");
        try {
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (IOException ioe) {
            LOGGER.error(""+ioe);
        }
    }
}
