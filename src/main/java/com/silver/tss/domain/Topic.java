package com.silver.tss.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Topic {

    private int id;
    private String topicId;
    private String topicName;
    private String topicType;
    private String topicDescription;
    private int topicMaxSelected;
    private int topicRealSelected;
    private Boolean yn;
    private Date createTime;
    private Date modifyTime;
}
