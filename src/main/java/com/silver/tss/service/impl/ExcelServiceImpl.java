package com.silver.tss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.common.Response;
import com.silver.tss.dao.StudentMapper;
import com.silver.tss.dao.TeacherMapper;
import com.silver.tss.dao.TopicMapper;
import com.silver.tss.dao.UserMapper;
import com.silver.tss.domain.*;
import com.silver.tss.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelServiceImpl.class);

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public JSONObject importStudentsExcel(String fileName, MultipartFile file) {

        Workbook wb = getReadWorkBook(fileName, file);
        if (wb == null) return Response.response(400);

        int studentCount = 0;
        int userConut = 0;
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            LOGGER.info("一共{}行", sheet.getPhysicalNumberOfRows());
            for (int r = 3; r < sheet.getPhysicalNumberOfRows(); r++) {
                Row row = sheet.getRow(r);
                LOGGER.info("第{}行", r);
                if (row == null) continue;
                String tmp = String.valueOf(row.getCell(2).getNumericCellValue());
                String classId = tmp.substring(0, tmp.lastIndexOf("."));
                LOGGER.info("classId={}", classId);
                String studentId = row.getCell(3).getStringCellValue();
                LOGGER.info("studentId={}", studentId);
                String studentName = row.getCell(4).getStringCellValue();

                Student student = new Student();
                student.setStudentId(studentId);
                student.setStudentName(studentName);
                student.setClassId(classId);
                student.setTopicId("null");
                student.setTopicName("null");
                student.setYn(true);
                student.setCreateTime(new Date());
                student.setModifyTime(new Date());

                User user = new User();
                user.setStudentId(studentId);
                user.setStudentPwd("666666");
                user.setStudentStatus(false);
                user.setYn(true);
                user.setCreateTime(new Date());
                user.setModifyTime(new Date());

                studentCount += studentMapper.insert(student);
                userConut += userMapper.insert(user);
            }
        }

        try {
            wb.close();
        } catch (Exception e) {
            LOGGER.info("" + e);
        }

        return Response.response(200);
    }

    @Override
    public JSONObject importTeachersExcel(String fileName, MultipartFile file) {

        Workbook wb = getReadWorkBook(fileName, file);
        if (wb == null) return Response.response(400);

        int teacherConut = 0;
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            for (int r = 1; r <= sheet.getPhysicalNumberOfRows(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                String classId = row.getCell(0).getStringCellValue();
                String teacherId = row.getCell(1).getStringCellValue();
                String teacherName = row.getCell(2).getStringCellValue();
                String authority = row.getCell(3).getStringCellValue();

                Teacher teacher = new Teacher();
                teacher.setClassId(classId);
                teacher.setTeacherId(teacherId);
                teacher.setTeacherName(teacherName);
                teacher.setAuthority(authority);
                teacher.setYn(true);
                teacher.setCreateTime(new Date());
                teacher.setModifyTime(new Date());

                teacherConut += teacherMapper.insert(teacher);
            }
        }

        try {
            wb.close();
        } catch (Exception e) {
            LOGGER.info("" + e);
        }

        return Response.response(200);
    }

    @Override
    public JSONObject importTopicsExcel(String fileName, MultipartFile file) {

        Workbook wb = getReadWorkBook(fileName, file);
        if (wb == null) return Response.response(400);

        int topicConut = 0;
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            for (int r = 1; r <= sheet.getPhysicalNumberOfRows(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                String tmp0 = String.valueOf(row.getCell(0).getNumericCellValue());
                String topicId = tmp0.substring(0, tmp0.lastIndexOf("."));
                String topicType = row.getCell(1).getStringCellValue();
                String topicName = row.getCell(2).getStringCellValue();
                String topicDescription = row.getCell(3).getStringCellValue();
                String tmp4 = String.valueOf(row.getCell(4).getNumericCellValue());
                String topicMaxSelected = tmp4.substring(0, tmp4.lastIndexOf("."));

                Topic topic = new Topic();
                topic.setTopicId(topicId);
                topic.setTopicName(topicName);
                topic.setTopicType(topicType);
                topic.setTopicDescription(topicDescription);
                topic.setTopicMaxSelected(Integer.valueOf(topicMaxSelected));
                topic.setTopicRealSelected1(0);
                topic.setTopicRealSelected2(0);
                topic.setTopicRealSelected3(0);
                topic.setYn(true);
                topic.setCreateTime(new Date());
                topic.setModifyTime(new Date());

                topicConut += topicMapper.insert(topic);
            }
        }

        try {
            wb.close();
        } catch (Exception e) {
            LOGGER.info("" + e);
        }

        return Response.response(200);
    }

    @Override
    public Workbook exportStudentsExcel(String classId) {

        StudentExample se = new StudentExample();
        if (Integer.parseInt(classId) == -1) se.createCriteria().andYnEqualTo(true);
        else se.createCriteria().andClassIdEqualTo(classId).andYnEqualTo(true);
        List<Student> students = studentMapper.selectByExample(se);

        Workbook wb = new HSSFWorkbook();
        HSSFSheet sheet = ((HSSFWorkbook) wb).createSheet();
        HSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("学号");
        row0.createCell(1).setCellValue("姓名");
        row0.createCell(2).setCellValue("班号");
        row0.createCell(3).setCellValue("题目");

        int r = 1;
        for (Student student : students) {
            HSSFRow row = sheet.createRow(r++);
            row.createCell(0).setCellValue(student.getStudentId());
            row.createCell(1).setCellValue(student.getStudentName());
            row.createCell(2).setCellValue(student.getClassId());
            row.createCell(3).setCellValue(student.getTopicName());
        }

        return wb;
    }

    private Workbook getReadWorkBook(String fileName, MultipartFile file) {
        Workbook wb;
        try {
            if (fileName.matches("^.+\\.(?i)(xls)$")) {
                wb = new HSSFWorkbook(file.getInputStream());
            } else if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
                wb = new XSSFWorkbook(file.getInputStream());
            } else {
                LOGGER.error("文件({})上传格式错误", fileName);
                return null;
            }
        } catch (Exception e) {
            LOGGER.info("" + e);
            return null;
        }
        return wb;
    }
}
