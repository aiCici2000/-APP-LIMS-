package com.example.lims.model.bean;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/2/23 22:01
 * Describe:
 */
public class SoftwareData {

    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [{"id":1,"name":"visio 2003","laboratoryId":0},{"id":2,"name":"ATA","laboratoryId":0},{"id":3,"name":"百科园考试系统","laboratoryId":0},{"id":4,"name":"winRAR","laboratoryId":0},{"id":5,"name":"firefox","laboratoryId":0},{"id":6,"name":"极域电子教室","laboratoryId":0},{"id":7,"name":"Adobe reader","laboratoryId":0},{"id":8,"name":"visual studio 2010","laboratoryId":0},{"id":9,"name":"MS sql 2016","laboratoryId":0},{"id":10,"name":"MATLAB 2016","laboratoryId":0},{"id":11,"name":"Eclipse","laboratoryId":0},{"id":12,"name":"JAVA JDK 12","laboratoryId":0},{"id":13,"name":"3DMAX 2016","laboratoryId":0},{"id":14,"name":"ROSE","laboratoryId":0},{"id":15,"name":"photoshop XO","laboratoryId":0},{"id":16,"name":"R语言","laboratoryId":0},{"id":17,"name":"Anaconda 3","laboratoryId":0},{"id":18,"name":"DosBOX","laboratoryId":0},{"id":19,"name":"MASM 2015","laboratoryId":0},{"id":20,"name":"MASM 611","laboratoryId":0},{"id":21,"name":"NI-circuit-design-suite","laboratoryId":0},{"id":22,"name":"Xshell","laboratoryId":0},{"id":23,"name":"Xftp","laboratoryId":0},{"id":24,"name":"vmvare work scation 12","laboratoryId":0},{"id":25,"name":"centos 7","laboratoryId":0},{"id":26,"name":"virtuel BOX","laboratoryId":0},{"id":27,"name":"packetTracer 7","laboratoryId":0},{"id":28,"name":"VC++ 6.0","laboratoryId":0}]
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
         * name : visio 2003
         * laboratoryId : 0
         */

        private int id;
        private String name;
        private int laboratoryId;

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

        public int getLaboratoryId() {
            return laboratoryId;
        }

        public void setLaboratoryId(int laboratoryId) {
            this.laboratoryId = laboratoryId;
        }
    }
}
