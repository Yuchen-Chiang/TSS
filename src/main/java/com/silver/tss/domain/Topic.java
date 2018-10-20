package com.silver.tss.domain;

import java.util.Date;

public class Topic {
    private Integer id;

    private String topicId;

    private String topicName;

    private String topicType;

    private String topicDescription;

    private Integer topicMaxSelected;

    private Integer topicRealSelected;

    private Boolean yn;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public Integer getTopicMaxSelected() {
        return topicMaxSelected;
    }

    public void setTopicMaxSelected(Integer topicMaxSelected) {
        this.topicMaxSelected = topicMaxSelected;
    }

    public Integer getTopicRealSelected() {
        return topicRealSelected;
    }

    public void setTopicRealSelected(Integer topicRealSelected) {
        this.topicRealSelected = topicRealSelected;
    }

    public Boolean getYn() {
        return yn;
    }

    public void setYn(Boolean yn) {
        this.yn = yn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}