package com.example.lims.model.bean;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/1/10 17:06
 * Describe:
 */
public class LaboratoryData {
    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [{"id":1,"name":"信息系统与理论实验室","number":"南区实训楼301-303","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":1,"equipmentNum":76,"notice":null},{"id":2,"name":"计算机软件与理论实验室","number":"南区实训楼302-304","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":1,"equipmentNum":81,"notice":null},{"id":3,"name":"数字媒体技术实验室","number":"南区实训楼317-319","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":2,"equipmentNum":44,"notice":null},{"id":4,"name":"虚拟现实技术实验室","number":"南区实训楼340-342","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":2,"equipmentNum":76,"notice":null},{"id":5,"name":"计算机技术与应用实验室","number":"南区实训楼414-416","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":3,"equipmentNum":74,"notice":null},{"id":6,"name":"虚拟化实验室","number":"南区实训楼420-422","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":4,"equipmentNum":22,"notice":null},{"id":7,"name":"计算机网络原理实验室","number":"南区实训楼424-426","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":5,"equipmentNum":74,"notice":null},{"id":8,"name":"云计算实验室","number":"南区实训楼430-432","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":5,"equipmentNum":73,"notice":null},{"id":9,"name":"计算机多媒体技术实验室","number":"南区实训楼436-438","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":6,"equipmentNum":74,"notice":null},{"id":10,"name":"大数据实验室","number":"南区实训楼442-444","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":3,"equipmentNum":81,"notice":null},{"id":11,"name":"微机原理与接口技术实验室","number":"南区实训楼321-323","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":7,"equipmentNum":81,"notice":null},{"id":12,"name":"计算机组成原理实验室","number":"南区实训楼348-350","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":7,"equipmentNum":81,"notice":null},{"id":13,"name":"虚拟仿真实验室","number":"南区实训楼217-219","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":6,"equipmentNum":40,"notice":null},{"id":14,"name":"大学生创新创业教室","number":"南区实训楼434","summary":"无","status":1,"course":null,"teacherId":0,"administratorId":8,"equipmentNum":1,"notice":null},{"id":15,"name":"计算机维修间","number":"南区实训楼440","summary":"无","status":0,"course":null,"teacherId":0,"administratorId":9,"equipmentNum":1,"notice":null}]
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
         * number : 南区实训楼301-303
         * summary : 无
         * status : 0
         * course : null
         * teacherId : 0
         * administratorId : 1
         * equipmentNum : 76
         * notice : null
         */

        private int id;
        private String name;
        private String number;
        private String summary;
        private int status;
        private Object course;
        private int teacherId;
        private int administratorId;
        private int equipmentNum;
        private Object notice;

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

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getCourse() {
            return course;
        }

        public void setCourse(Object course) {
            this.course = course;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public int getAdministratorId() {
            return administratorId;
        }

        public void setAdministratorId(int administratorId) {
            this.administratorId = administratorId;
        }

        public int getEquipmentNum() {
            return equipmentNum;
        }

        public void setEquipmentNum(int equipmentNum) {
            this.equipmentNum = equipmentNum;
        }

        public Object getNotice() {
            return notice;
        }

        public void setNotice(Object notice) {
            this.notice = notice;
        }
    }
}
