package com.example.lims.model;

/**
 * @Author：李壮
 * @Package：com.example.lims.model
 * @Date：2023/1/30 16:16
 * Describe:
 */
public class MaterialItem {

    private String name;
    private int num;
    private String other;

    public MaterialItem(String name, int num, String other) {
        this.name = name;
        this.num = num;
        this.other = other;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
