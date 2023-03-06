package com.example.lims.model.bean;

import java.util.Date;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/1/10 17:19
 * Describe:
 */
public class ApplicationData {

    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [{"id":1,"applicantId":10,"applyName":"张三","status":2,"materialId":2,"materialName":"5类水晶头","num":10,"adminId":5,"time":"2023-02-07T01:33:18.000+00:00"}]
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
         * applicantId : 10
         * applyName : 张三
         * status : 2
         * materialId : 2
         * materialName : 5类水晶头
         * num : 10
         * adminId : 5
         * time : 2023-02-07T01:33:18.000+00:00
         */

        private int id;
        private int applicantId;
        private String applyName;
        private int status;
        private int materialId;
        private String materialName;
        private int num;
        private int adminId;
        private Date time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getApplicantId() {
            return applicantId;
        }

        public void setApplicantId(int applicantId) {
            this.applicantId = applicantId;
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

        public int getMaterialId() {
            return materialId;
        }

        public void setMaterialId(int materialId) {
            this.materialId = materialId;
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
    }
}
