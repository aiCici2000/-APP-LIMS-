package com.example.lims.model.bean;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/1/10 16:24
 * Describe:
 */
public class UserData {


    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [{"id":1,"userNumber":"0000000000","name":"黄春雨","password":"111111","email":null,"sex":1,"type":2,"laboratoryId":0},{"id":2,"userNumber":"0000000000","name":"胡汉平","password":"111111","email":null,"sex":1,"type":2,"laboratoryId":0},{"id":3,"userNumber":"0000000000","name":"陈毓","password":"111111","email":null,"sex":0,"type":2,"laboratoryId":0},{"id":4,"userNumber":"0000000000","name":"李华","password":"111111","email":null,"sex":0,"type":2,"laboratoryId":0},{"id":5,"userNumber":"0000000000","name":"李誌","password":"111111","email":null,"sex":1,"type":2,"laboratoryId":0},{"id":6,"userNumber":"0000000000","name":"高宁","password":"111111","email":null,"sex":0,"type":2,"laboratoryId":0},{"id":7,"userNumber":"0000000000","name":"杨宏伟","password":"111111","email":null,"sex":1,"type":2,"laboratoryId":0},{"id":8,"userNumber":"0000000000","name":"王睿","password":"111111","email":null,"sex":1,"type":2,"laboratoryId":0},{"id":9,"userNumber":"0000000000","name":"韩新民","password":"111111","email":null,"sex":1,"type":2,"laboratoryId":0}]
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
         * userNumber : 0000000000
         * name : 黄春雨
         * password : 111111
         * email : null
         * sex : 1
         * type : 2
         * laboratoryId : 0
         */

        private int id;
        private String userNumber;
        private String name;
        private String password;
        private Object email;
        private int sex;
        private int type;
        private int laboratoryId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserNumber() {
            return userNumber;
        }

        public void setUserNumber(String userNumber) {
            this.userNumber = userNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getLaboratoryId() {
            return laboratoryId;
        }

        public void setLaboratoryId(int laboratoryId) {
            this.laboratoryId = laboratoryId;
        }
    }
}
