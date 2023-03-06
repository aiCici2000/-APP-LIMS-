package com.example.lims.model.bean;

import java.util.Date;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/1/10 16:50
 * Describe:
 */
public class ReservationData {

    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [{"id":1,"type":1,"applicantId":10,"applicantName":"张三","courseNumber":4,"courseTime":"2023-02-07T01:30:51.000+00:00","status":2,"teacherId":10,"teacherName":"张三","laboratoryId":4,"labName":"虚拟现实技术实验室","labNumber":"南区实训楼340-342","courseName":"虚拟现实技术","time":"2023-02-07T01:31:53.000+00:00"}]
     */

    private int status;
    private String msg;
    private boolean success;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * type : 1
         * applicantId : 10
         * applicantName : 张三
         * courseNumber : 4
         * courseTime : 2023-02-07T01:30:51.000+00:00
         * status : 2
         * teacherId : 10
         * laboratoryId : 4
         * labName : 虚拟现实技术实验室
         * labNumber : 南区实训楼340-342
         * courseName : 虚拟现实技术
         * time : 2023-02-07T01:31:53.000+00:00
         */

        private int id;
        private int type;
        private int applicantId;
        private String applicantName;
        private int courseNumber;
        private Date courseTime;
        private int status;
        private int teacherId;
        private int laboratoryId;
        private String labName;
        private String labNumber;
        private String courseName;
        private Date time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getApplicantId() {
            return applicantId;
        }

        public void setApplicantId(int applicantId) {
            this.applicantId = applicantId;
        }

        public String getApplicantName() {
            return applicantName;
        }

        public void setApplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public int getCourseNumber() {
            return courseNumber;
        }

        public void setCourseNumber(int courseNumber) {
            this.courseNumber = courseNumber;
        }

        public Date getCourseTime() {
            return courseTime;
        }

        public void setCourseTime(Date courseTime) {
            this.courseTime = courseTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public int getLaboratoryId() {
            return laboratoryId;
        }

        public void setLaboratoryId(int laboratoryId) {
            this.laboratoryId = laboratoryId;
        }

        public String getLabName() {
            return labName;
        }

        public void setLabName(String labName) {
            this.labName = labName;
        }

        public String getLabNumber() {
            return labNumber;
        }

        public void setLabNumber(String labNumber) {
            this.labNumber = labNumber;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }
    }
}
