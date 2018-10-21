package com.silver.tss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.silver.tss.common.Response;
import com.silver.tss.dao.StudentMapper;
import com.silver.tss.dao.TeacherMapper;
import com.silver.tss.dao.TopicMapper;
import com.silver.tss.dao.UserMapper;
import com.silver.tss.domain.Student;
import com.silver.tss.domain.Teacher;
import com.silver.tss.domain.Topic;
import com.silver.tss.domain.User;
import com.silver.tss.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

        String classId = file.getName();
        Workbook wb = getReadWorkBook(fileName, file);
        if (wb == null) return Response.response(400);

        int studentCount = 0;
        int userConut = 0;
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            for (int r = 1; r < sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                String studentId = row.getCell(1).getStringCellValue();
                String studentName = row.getCell(2).getStringCellValue();

                Student student = new Student();
                student.setStudentId(studentId);
                student.setStudentName(studentName);
                student.setClassId(classId);
                student.setTopicId("null");
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
            for (int r = 1; r < sheet.getLastRowNum(); r++) {
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
            for (int r = 1; r < sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                String topicId = row.getCell(0).getStringCellValue();
                String topicName = row.getCell(1).getStringCellValue();
                String topicType = row.getCell(2).getStringCellValue();
                String topicDescription = row.getCell(3).getStringCellValue();
                String topicMaxSelected = row.getCell(4).getStringCellValue();

                Topic topic = new Topic();
                topic.setTopicId(topicId);
                topic.setTopicName(topicName);
                topic.setTopicType(topicType);
                topic.setTopicDescription(topicDescription);
                topic.setTopicMaxSelected(Integer.valueOf(topicMaxSelected));
                topic.setTopicRealSelected(0);
                topic.setYn(true);
                topic.setCreateTime(new Date());
                topic.setCreateTime(new Date());

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
    public ResponseEntity exportStudentsExcel(String classId) {
        return null;
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
