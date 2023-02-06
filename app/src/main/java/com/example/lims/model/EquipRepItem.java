package com.example.lims.model;

/**
 * @Author：李壮
 * @Package：com.example.lims.model
 * @Date：2023/1/18 11:06
 * Describe:
 */
public class EquipRepItem {

    // 0-标题item；1-内容item,后台传过来
    private int type;
    private String name;
    private String labName;

    public EquipRepItem(int type, String name, String labName) {
        this.type = type;
        this.name = name;
        this.labName = labName;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
