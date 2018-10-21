package com.silver.tss.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

    public JSONObject importStudentsExcel(String fileName, MultipartFile file);

    public JSONObject importTeachersExcel(String fileName, MultipartFile file);

    public JSONObject importTopicsExcel(String fileName, MultipartFile file);

    public Workbook exportStudentsExcel(String classId);
}
