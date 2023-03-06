package com.example.lims.model.bean;

import java.util.Date;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/1/10 16:43
 * Describe:
 */
public class SoftwareApplyData {

    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [{"id":1,"softwareId":1,"softwareName":"visio 2003","applicantId":10,"applicantName":"张三","adminId":5,"time":"2023-02-07T01:27:23.000+00:00","status":2,"labId":3,"labName":"数字媒体技术实验室","labNumber":"南区实训楼317-319"},{"id":2,"softwareId":5,"softwareName":"ATA","applicantId":10,"applicantName":"张三","adminId":5,"time":"2023-02-07T01:28:28.000+00:00","status":2,"labId":4,"labName":"虚拟现实技术实验室","labNumber":"南区实训楼340-342"}]
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
         * softwareId : 1
         * softwareName : visio 2003
         * applicantId : 10
         * applicantName : 张三
         * adminId : 5
         * time : 2023-02-07T01:27:23.000+00:00
         * status : 2
         * labId : 3
         * labName : 数字媒体技术实验室
         * labNumber : 南区实训楼317-319
         */

        private int id;
        private int softwareId;
        private String softwareName;
        private int applicantId;
        private String applicantName;
        private int adminId;
        private Date time;
        private int status;
        private int labId;
        private String labName;
        private String labNumber;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSoftwareId() {
            return softwareId;
        }

        public void setSoftwareId(int softwareId) {
            this.softwareId = softwareId;
        }

        public String getSoftwareName() {
            return softwareName;
        }

        public void setSoftwareName(String softwareName) {
            this.softwareName = softwareName;
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

        public int getAdminId() {
            return adminId;
        }

        public void setAdminId(int adminId) {
            this.adminId = adminId;
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

        public int getLabId() {
            return labId;
        }

        public void setLabId(int labId) {
            this.labId = labId;
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
    }
}
