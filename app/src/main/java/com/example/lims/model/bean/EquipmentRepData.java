package com.example.lims.model.bean;

import java.util.Date;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/2/15 14:39
 * Describe:
 */
public class EquipmentRepData {


    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [{"id":1,"name":"信息系统与理论实验室","type":0},{"id":2,"name":"学生计算机","type":1},{"id":3,"name":"学生计算机","type":1},{"id":4,"name":"学生计算机","type":1},{"id":5,"name":"教师,学生桌","type":1},{"id":6,"name":"教师,学生桌","type":1},{"id":7,"name":"教师，学生椅","type":1},{"id":8,"name":"教师，学生椅","type":1},{"id":9,"name":"教师，学生椅","type":1},{"id":10,"name":"投影仪","type":1},{"id":11,"name":"幕布","type":1},{"id":12,"name":"麦克","type":1},{"id":13,"name":"音箱","type":1},{"id":14,"name":"交换机","type":1},{"id":15,"name":"AP","type":1},{"id":16,"name":"窗户","type":1},{"id":17,"name":"窗帘","type":1},{"id":18,"name":"空调","type":1},{"id":2,"name":"计算机软件与理论实验室","type":0}]
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
         * name : 信息系统与理论实验室
         * type : 0
         */

        // 实验室 id / 设备 id
        private int id;

        // 实验室编号 / 设备名
        private String name;

        // 类型，标题/设备
        private int type;

        // 提供人 id
        private int providerId;

        // 提供人类型; 0-学生；1-任课教师；2-实验室管理教师
        private int providerType;

        // 提供人 number
        private String providerNumber;

        private String providerName;

        // 提供时间
        private Date time;

        // 维护意见
        private String opinion;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getProviderId() {
            return providerId;
        }

        public void setProviderId(int providerId) {
            this.providerId = providerId;
        }

        public int getProviderType() {
            return providerType;
        }

        public void setProviderType(int providerType) {
            this.providerType = providerType;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public String getOpinion() {
            return opinion;
        }

        public void setOpinion(String opinion) {
            this.opinion = opinion;
        }

        public String getProviderNumber() {
            return providerNumber;
        }

        public void setProviderNumber(String providerNumber) {
            this.providerNumber = providerNumber;
        }

        public String getProviderName() {
            return providerName;
        }

        public void setProviderName(String providerName) {
            this.providerName = providerName;
        }
    }
}
