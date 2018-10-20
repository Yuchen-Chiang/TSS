package com.silver.tss.domain;

import java.util.Date;

public class Status {
    private Integer id;

    private Integer tssStatus;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTssStatus() {
        return tssStatus;
    }

    public void setTssStatus(Integer tssStatus) {
        this.tssStatus = tssStatus;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}