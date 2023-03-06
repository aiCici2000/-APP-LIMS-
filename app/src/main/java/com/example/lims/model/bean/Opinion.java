package com.example.lims.model.bean;

import java.util.Date;

/**
 * @Author：李壮
 * @Package：com.example.lims.model.bean
 * @Date：2023/1/10 17:01
 * Describe:
 */
public class Opinion {

    // 设备 id
    private int equipmentId;

    // 该设备维护意见
    private String opinion;

    // 提供维护意见人 id
    private int userId;

    // 时间
    private String time;

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
