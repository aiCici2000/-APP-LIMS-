package com.example.lims.model;

/**
 * @Author：李壮
 * @Package：com.example.lims.model
 * @Date：2023/1/8 23:38
 * Describe:
 */
public class HomeItem {
    private int image;
    private String text;
    private int action;

    public HomeItem(int image, String text, int action) {
        this.image = image;
        this.text = text;
        this.action = action;
    }
    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
