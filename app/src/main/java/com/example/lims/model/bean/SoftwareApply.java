package com.example.lims.model.bean;

import java.util.Date;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/1/10 16:40
 * Describe:
 */
public class SoftwareApply {

    // 申请的软件 id
    private int softwareId;

    // 申请人 id
    private int applicantId;

    // 审核人 id
    private int adminId;

    // 申请时间
    private String time;

    // 申请状态；0-失败；1-成功；2-待审核
    private int status;

    // 所属实验室id
    private int labId;

    public int getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    @Override
    public String toString() {
        return "SoftwareApply{" +
                "softwareId=" + softwareId +
                ", applicantId=" + applicantId +
                ", adminId=" + adminId +
                ", time='" + time + '\'' +
                ", status=" + status +
                ", labId=" + labId +
                '}';
    }
}
