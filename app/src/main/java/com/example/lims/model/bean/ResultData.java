package com.example.lims.model.bean;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/2/14 17:23
 * Describe:
 */
public class ResultData {

    /**
     * status : 200
     * msg : 操作成功！
     * success : true
     * data : null
     */

    private int status;
    private String msg;
    private boolean success;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
