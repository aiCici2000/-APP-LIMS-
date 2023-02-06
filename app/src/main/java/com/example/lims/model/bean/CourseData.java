package com.example.lims.model.bean;

import java.util.Date;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/1/10 17:16
 * Describe:
 */
public class CourseData {

    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [{"id":1,"name":"计算机组成原理","teacherId":7,"teacherName":"杨宏伟","laboratoryId":12,"laboratoryNumber":"南区实训楼348-350","courseNumber":3,"time":"2023-02-02T09:12:14.000+00:00","num":0}]
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
         * name : 计算机组成原理
         * teacherId : 7
         * teacherName : 杨宏伟
         * laboratoryId : 12
         * laboratoryNumber : 南区实训楼348-350
         * courseNumber : 3
         * time : 2023-02-02T09:12:14.000+00:00
         * num : 0
         */

        private int id;
        private String name;
        private int teacherId;
        private String teacherName;
        private int laboratoryId;
        private String laboratoryNumber;
        private int courseNumber;
        private Date time;
        private int num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public int getLaboratoryId() {
            return laboratoryId;
        }

        public void setLaboratoryId(int laboratoryId) {
            this.laboratoryId = laboratoryId;
        }

        public String getLaboratoryNumber() {
            return laboratoryNumber;
        }

        public void setLaboratoryNumber(String laboratoryNumber) {
            this.laboratoryNumber = laboratoryNumber;
        }

        public int getCourseNumber() {
            return courseNumber;
        }

        public void setCourseNumber(int courseNumber) {
            this.courseNumber = courseNumber;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
