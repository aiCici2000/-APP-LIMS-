package com.example.lims.utils;

import static com.example.lims.utils.Constant.ADMIN_AGREED_LAB_APPLY;
import static com.example.lims.utils.Constant.ADMIN_AGREED_MATERIAL_APPLY;
import static com.example.lims.utils.Constant.ADMIN_AGREED_SOFTWARE_APPLY;
import static com.example.lims.utils.Constant.EQUIPMENT_REPAIR_OPINION;
import static com.example.lims.utils.Constant.OTHER_REPORTING;
import static com.example.lims.utils.Constant.STUDENT_RESERVE_EXPERIMENT;
import static com.example.lims.utils.Constant.TEACHER_AGREED_EXPERIMENT_RESERVATION;
import static com.example.lims.utils.Constant.TEACHER_APPLY_LAB;
import static com.example.lims.utils.Constant.TEACHER_APPLY_MATERIAL;
import static com.example.lims.utils.Constant.TEACHER_APPLY_SOFTWARE;

import com.alibaba.fastjson.JSONObject;
import com.example.lims.model.bean.Message;
import com.example.lims.model.bean.MessageData;

/**
 * @Author：李壮
 * @Package：com.example.lims.utils
 * @Date：2023/3/1 14:31
 * Describe:
 */
public class MessageUtil {

    public static String getMessage(MessageData.DataBean message) {

        String type = null;
        String topic = message.getConversationId();
        Message content = JSONObject.parseObject(message.getContent(), Message.class);
        int userType = content.getUserType();
        String userName = content.getUserName();
        if (userType == 0) {
            type = "同学";
        } else if (userType == 1) {
            type = "教师";
        } else if (userType == 2) {
            type = "管理员";
        }
        switch (topic) {
            case TEACHER_AGREED_EXPERIMENT_RESERVATION:
                return userName + " " + type + " , " + "已经审批 你的实验预约";
            case STUDENT_RESERVE_EXPERIMENT:
                return userName + " " + type + " , " + "向您预约 实验";
            case ADMIN_AGREED_LAB_APPLY:
                return userName + " " + type + " , " + "已经审批 您的实验室申请";
            case ADMIN_AGREED_MATERIAL_APPLY:
                return userName + " " + type + " , " + "已经审批 您的耗材申请";
            case ADMIN_AGREED_SOFTWARE_APPLY:
                return userName + " " + type + " , " + "已经审批 您的设备软件申请";
            case TEACHER_APPLY_LAB:
                return userName + " " + type + " , " + "向您申请 实验室进行开课";
            case TEACHER_APPLY_MATERIAL:
                return userName + " " + type + " , " + "向您申请 实验室耗材";
            case TEACHER_APPLY_SOFTWARE:
                return userName + " " + type + " , " + "向您申请 安装设备软件";
            case OTHER_REPORTING:
                return userName + " " + type + " , " + "向您报备：" + content.getContent();
            case EQUIPMENT_REPAIR_OPINION:
                return userName + " " + type + " , " + "向您提出 设备维护意见";
            default:
                return null;
        }
    }

}
