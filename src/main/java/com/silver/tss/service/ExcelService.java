package com.silver.tss.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

    public JSONObject importStudentsExcel(String fileName, MultipartFile file);

    public JSONObject importTeachersExcel(String fileName, MultipartFile file);

    public JSONObject importTopicsExcel(String fileName, MultipartFile file);

    public ResponseEntity exportStudentsExcel(String classId);
}
