package com.example.lims.model.bean;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/3/1 14:54
 * Describe:
 */
public class Message {

    private int userId;

    private String userName;

    private int userType;

    private String content;

    public Message() {
    }

    public Message(int userId, String userName, int userType) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
