package com.example.lims.model;

import java.util.Date;

/**
 * @Author：李壮
 * @Package：com.example.lims.model
 * @Date：2023/1/17 15:45
 * Describe:
 */
public class RatifyLabApplyItem {

    private String labNum;
    private Date date;
    private int courseNum;
    private String courseName;
    private String applyName;
    private int status;
    private Date time;

    public RatifyLabApplyItem(String labNum, Date date, int courseNum, String courseName, String applyName, int status, Date time) {
        this.labNum = labNum;
        this.date = date;
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.applyName = applyName;
        this.status = status;
        this.time = time;
    }

    public String getLabNum() {
        return labNum;
    }

    public void setLabNum(String labNum) {
        this.labNum = labNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
