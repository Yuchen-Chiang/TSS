package com.silver.tss.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Student {

    private int id;
    private String studentId;
    private String studentName;
    private String studentPwd;
    private Boolean studentStatus;
    private String classId;
    private String topicId;
    private Boolean yn;
    private Date createTime;
    private Date modifyTime;
}
