package com.example.lims.model.bean;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/2/22 17:26
 * Describe:
 */
public class Material {

    private String name;
    private String num;
    private Object other;

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
