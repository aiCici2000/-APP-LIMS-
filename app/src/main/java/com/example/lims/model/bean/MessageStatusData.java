package com.example.lims.model.bean;

import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/3/2 8:56
 * Describe:
 */
public class MessageStatusData {
    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : [0,0,0,0]
     */

    private int status;
    private String msg;
    private boolean success;
    private List<Integer> data;

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

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
