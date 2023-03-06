package com.example.lims.model.bean;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/1/10 17:02
 * Describe:
 */
public class MaterialData {

    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [{"id":1,"name":"超五类双绞线","num":"50","other":null},{"id":2,"name":"5类水晶头","num":"100","other":null},{"id":3,"name":"6类双绞线","num":"50","other":"维修用"},{"id":4,"name":"6类水晶头","num":"100","other":"维修用"},{"id":5,"name":"拖布","num":"20","other":null},{"id":6,"name":"条扫","num":"20","other":null},{"id":7,"name":"撮子","num":"10","other":null},{"id":8,"name":"毛巾","num":"50","other":null},{"id":9,"name":"中性笔","num":"150","other":"红黑两色"},{"id":10,"name":"记号笔","num":"50","other":null},{"id":11,"name":"白板笔","num":"50","other":null},{"id":12,"name":"5号充电电池","num":"40","other":"3000毫安以上"},{"id":13,"name":"7号充电电池","num":"40","other":"1000毫安以上"},{"id":14,"name":"5号电池充电器","num":"2","other":"带充满控制"},{"id":15,"name":"7号电池充电器","num":"2","other":"带充满控制"},{"id":16,"name":"9号充电电池","num":"10","other":null},{"id":17,"name":"9号电池充电器","num":"2","other":null}]
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
         * name : 超五类双绞线
         * num : 50
         * other : null
         */

        private int id;
        private String name;
        private String num;
        private Object other;

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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public Object getOther() {
            return other;
        }

        public void setOther(Object other) {
            this.other = other;
        }
    }
}
