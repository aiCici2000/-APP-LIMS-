package com.example.lims.model;

import java.util.Date;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/1/17 22:50
 * Describe:
 */
public class RatifyMaApplyItem {

    private String materialName;
    private int num;
    private String applyName;
    private Date time;
    private int status;

    public RatifyMaApplyItem(String materialName, int num, String applyName, Date time, int status) {
        this.materialName = materialName;
        this.num = num;
        this.applyName = applyName;
        this.time = time;
        this.status = status;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
