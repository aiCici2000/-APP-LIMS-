package com.example.lims.model.bean;

import java.util.Date;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/3/1 10:52
 * Describe:
 */
public class MessageData {


    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [{"id":1,"fromId":11,"toId":1,"conversationId":"t_a_m","content":"{\"userType\":1,\"time\":\"2023-02-28\",\"userName\":\"teacher\",\"userId\":11}","status":0,"createTime":"2023-02-28T05:54:17.000+00:00"},{"id":2,"fromId":11,"toId":1,"conversationId":"t_a_m","content":"{\"userType\":1,\"userName\":\"teacher\",\"userId\":11}","status":0,"createTime":"2023-02-28T06:06:10.000+00:00"},{"id":10,"fromId":11,"toId":1,"conversationId":"t_a_m","content":"{\"userType\":1,\"userName\":\"teacher\",\"userId\":11}","status":0,"createTime":"2023-02-28T08:30:15.000+00:00"},{"id":11,"fromId":11,"toId":1,"conversationId":"t_a_m","content":"{\"userType\":1,\"userName\":\"teacher\",\"userId\":11}","status":0,"createTime":"2023-02-28T08:30:21.000+00:00"}]
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
         * fromId : 11
         * toId : 1
         * conversationId : t_a_m
         * content : {"userType":1,"time":"2023-02-28","userName":"teacher","userId":11}
         * status : 0
         * createTime : 2023-02-28T05:54:17.000+00:00
         */

        private int id;
        private int fromId;
        private int toId;
        private String conversationId;
        private String content;
        private int status;
        private Date createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFromId() {
            return fromId;
        }

        public void setFromId(int fromId) {
            this.fromId = fromId;
        }

        public int getToId() {
            return toId;
        }

        public void setToId(int toId) {
            this.toId = toId;
        }

        public String getConversationId() {
            return conversationId;
        }

        public void setConversationId(String conversationId) {
            this.conversationId = conversationId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
    }
}
