package com.example.lims.model.bean;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/2/24 15:55
 * Describe:
 */
public class User {

    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : {"id":5,"userNumber":"0000000005","name":"李誌","password":"111111","email":null,"sex":1,"type":2,"laboratoryId":0}
     */

    private int status;
    private String msg;
    private boolean success;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5
         * userNumber : 0000000005
         * name : 李誌
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
