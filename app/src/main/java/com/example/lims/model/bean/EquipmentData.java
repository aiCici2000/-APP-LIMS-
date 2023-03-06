package com.example.lims.model.bean;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/2/16 9:20
 * Describe:
 */
public class EquipmentData {


    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [{"id":2,"name":"学生计算机","number":"联想","summary":null,"labId":1,"status":1,"type":1,"inTime":"2023-01-11 14:29:32","repairTime":null},{"id":3,"name":"学生计算机","number":"启天M690E","summary":null,"labId":1,"status":2,"type":1,"inTime":"2023-01-11 14:31:32","repairTime":null},{"id":4,"name":"学生计算机","number":"DELL380","summary":null,"labId":1,"status":1,"type":1,"inTime":"2023-01-11 14:33:32","repairTime":null},{"id":5,"name":"教师,学生桌","number":"新（教师）","summary":null,"labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:34:32","repairTime":null},{"id":6,"name":"教师,学生桌","number":"双机位（新）","summary":null,"labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:35:32","repairTime":null},{"id":7,"name":"教师，学生椅","number":"黑布（教师）旧","summary":null,"labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:36:32","repairTime":null},{"id":8,"name":"教师，学生椅","number":"皮（旧）","summary":null,"labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:37:32","repairTime":null},{"id":9,"name":"教师，学生椅","number":"黑布（旧）","summary":null,"labId":1,"status":2,"type":2,"inTime":"2023-01-11 14:38:32","repairTime":null},{"id":10,"name":"投影仪","number":"NECNPM403","summary":"1个遥控器","labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:39:32","repairTime":null},{"id":11,"name":"幕布","number":"红叶400","summary":null,"labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:39:52","repairTime":null},{"id":12,"name":"麦克","number":"JUSBE","summary":"2（大，小）","labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:40:52","repairTime":null},{"id":13,"name":"音箱","number":"JUSBE","summary":null,"labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:41:52","repairTime":null},{"id":14,"name":"交换机","number":"H3CS5130","summary":null,"labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:42:52","repairTime":null},{"id":15,"name":"AP","number":"无","summary":null,"labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:43:52","repairTime":null},{"id":16,"name":"窗户","number":"无","summary":null,"labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:44:52","repairTime":null},{"id":17,"name":"窗帘","number":"无","summary":null,"labId":1,"status":2,"type":2,"inTime":"2023-01-11 14:45:52","repairTime":null},{"id":18,"name":"空调","number":"美的KFR72LDY","summary":"无遥控器","labId":1,"status":1,"type":2,"inTime":"2023-01-11 14:46:52","repairTime":null}]
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
         * id : 2
         * name : 学生计算机
         * number : 联想
         * summary : null
         * labId : 1
         * status : 1
         * type : 1
         * inTime : 2023-01-11 14:29:32
         * repairTime : null
         */

        private int id;
        private String name;
        private String number;
        private Object summary;
        private int labId;
        private int status;
        private int type;
        private String inTime;
        private Object repairTime;

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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
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

        public Object getRepairTime() {
            return repairTime;
        }

        public void setRepairTime(Object repairTime) {
            this.repairTime = repairTime;
        }
    }
}
