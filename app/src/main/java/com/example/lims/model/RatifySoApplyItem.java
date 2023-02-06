package com.example.lims.model;

import java.util.Date;

/**
 * @Author：李壮
 * @Package：com.example.lims.model
 * @Date：2023/1/18 9:02
 * Describe:
 */
public class RatifySoApplyItem {

    private String labName;
    private String softwareName;
    private String applyName;
    private Date time;

    private int status;
    public RatifySoApplyItem(String labName, String softwareName, String applyName, Date time, int status) {
        this.labName = labName;
        this.softwareName = softwareName;
        this.applyName = applyName;
        this.time = time;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
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
}
