package com.silver.tss.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

    JSONObject importStudentsExcel(String fileName, MultipartFile file);

    JSONObject importTeachersExcel(String fileName, MultipartFile file);

    JSONObject importTopicsExcel(String fileName, MultipartFile file);

    Workbook exportStudentsExcel(String classId);
}
