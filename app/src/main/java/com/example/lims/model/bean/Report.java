package com.example.lims.model.bean;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/1/10 16:59
 * Describe:
 */
public class Report {

    int fromId;

    int toId;

    String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
