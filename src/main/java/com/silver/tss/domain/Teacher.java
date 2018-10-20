package com.silver.tss.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Teacher {

    private int id;
    private String classId;
    private String teacherId;
    private String teacherName;
    private String authority;
    private Boolean yn;
    private Date createTime;
    private Date modifyTime;
}
