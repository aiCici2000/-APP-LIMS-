package com.example.lims.model.bean;

import java.util.Date;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/2/15 8:45
 * Describe:
 */
public class Equipment {

    // 设备名称
    private String name;

    // 设备型号；同一型号设备可有多个
    private String number;

    // 设备简介
    private String summary;

    // 所在实验室，关联实验室id
    private int labId;

    // 设备当前状态；0-未使用；1-使用中；2-需要维修
    private int status;

    // 类型；0-教师计算机；1-学生计算机；2-其他
    private int type;

    // 入库时间
    private String inTime;

    // 上次维护时间
    private String repairTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }
}
