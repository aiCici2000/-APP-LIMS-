package com.example.lims.model;

/**
 * @Author：李壮
 * @Package：com.example.lims.model
 * @Date：2023/1/12 9:34
 * Describe:
 */
public class LaboratoryItem {

    private int id;
    private String topText;
    private String bottomText;
    private int image;
    private int num;

    public LaboratoryItem(String topText, String bottomText, int image, int num) {
        this.bottomText = bottomText;
        this.topText = topText;
        this.image = image;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTopText() {
        return topText;
    }

    public void setTopText(String topText) {
        this.topText = topText;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
