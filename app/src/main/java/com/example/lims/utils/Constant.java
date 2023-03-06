package com.example.lims.utils;

import com.example.lims.R;
import com.example.lims.model.HomeItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.PUT;

/**
 * @Author：李壮
 * @Package：com.example.lims.model
 * @Date：2023/1/8 20:16
 * Describe:
 */
public class Constant {

    public static final String[] SENTENCES = new String[]{
            "”长风破浪会有时，直挂云帆济沧海“",
            "”比别人多一点执着，你就会创造奇迹“",
            "”用理想去成就人生，不要蹉跎了岁月“",
            "”把绊脚石，变成成功的基石“",
            "”为成功找方法，不为失败找借口“",
            "”成功者不是从不失败，而是从不放弃“",
            "”让未来的你，感谢今天的你所付出的努力“",
            "”有志者自有千方百计,无志者只感千难万难“",
            "”平凡的脚步也可以走完伟大的行程“",
            "”腹有诗书气自华，读书万卷始通神“"
    };

    public static final String URL = "http://192.168.238.119:8080/";

    public static final String HOME = "首页";
    public static final String MESSAGE = "消息";
    public static final String SETTING = "设置";
    public static final String LOGIN = "登录";

    public static final String TEACHER_LIST = "师资团队";
    public static final String LABORATORY_LIST = "实验室列表";
    public static final String OTHER_REPORT = "其他报备";
    public static final String ALL_COURSE = "当前所有课程";

    public static final String RATIFY_LABORATORY_APPLY = "审批实验室申请";
    public static final String RATIFY_MATERIAL_APPLY = "审批耗材申请";
    public static final String RATIFY_SOFTWARE_APPLY = "审批软件申请";
    public static final String EQUIPMENT_REPAIR_MANAGE = "设备维修管理";
    public static final String EQUIPMENT_INTO_MANAGE = "设备采购入库";
    public static final String EQUIPMENT_GOTO_MANAGE = "设备报废出库";
    public static final String MATERIAL_NUMBER = "耗材数量";
    public static final String ANNOUNCEMENT = "发布公告";
    public static final String STATISTICS_PRINTING = "统计打印";

    public static final String RATIFY_EXPERIMENT_RESERVATION = "审批实验预约";
    public static final String APPLY_LABORATORY = "申请实验室";
    public static final String APPLY_MATERIAL = "申请材料";
    public static final String APPLY_SOFTWARE = "申请设备软件";

    public static final String EXPERIMENT_RESERVATION = "实验预约";
    public static final String MY_RESERVATION = "我的预约";
    public static final String MY_COURSE = "我的课程";

    public static final int HAVE_DATA = 0;
    public static final int NO_DATA = 1;
    public static final int TITLE = 2;

    public static final int SUCCESS = 200;
    public static final int FAILED = 500;

    public static final int CLIENT_PARAMETER_IS_NULL = 400;

    public static final int UN_LOGIN = 501;
    public static final int USER_NOT_EXIST_ERROR = 516;
    public static final int PASSWORD_ERROR = 517;
    public static final int SYSTEM_NULL_POINTER = 543;
    public static final int INSUFFICIENT_QUANTITY = 589;

    // 消息的类型
    public static final int STUDENT_ITEM_1 = 0;

    public static final int TEACHER_ITEM_1 = 1;
    public static final int TEACHER_ITEM_2 = 2;

    public static final int ADMIN_ITEM_1 = 3;
    public static final int ADMIN_ITEM_2 = 4;
    public static final int ADMIN_ITEM_3 = 5;
    public static final int ADMIN_ITEM_4 = 6;

    /**
     * 主题：教师同意实验预约
     */
    public static final String TEACHER_AGREED_EXPERIMENT_RESERVATION = "t_a_e_r";


    /**
     * 主题：学生预约实验
     */
    public static final String STUDENT_RESERVE_EXPERIMENT = "s_r_e";

    /**
     * 主题：管理员同意实验室申请
     */
    public static final String ADMIN_AGREED_LAB_APPLY = "a_a_l_a";


    /**
     * 主题：管理员同意耗材申请
     */
    public static final String ADMIN_AGREED_MATERIAL_APPLY = "a_a_m_a";

    /**
     * 主题：管理员同意软件申请
     */
    public static final String ADMIN_AGREED_SOFTWARE_APPLY = "a_a_s_a";


    /**
     * 主题：教师申请实验室
     */
    public static final String TEACHER_APPLY_LAB = "t_a_l";


    /**
     * 主题：教师申请耗材
     */
    public static final String TEACHER_APPLY_MATERIAL = "t_a_m";

    /**
     * 主题：教师申请软件
     */
    public static final String TEACHER_APPLY_SOFTWARE = "t_a_s";


    /**
     * 主题：其他报备
     */
    public static final String OTHER_REPORTING = "o_r";


    /**
     * 主题：设备维护意见
     */
    public static final String EQUIPMENT_REPAIR_OPINION = "e_r_o";


    public static final List<HomeItem> itemList = new ArrayList<>();


    // 默认首页
    public static void initHomeItem() {

        itemList.clear();
        HomeItem item1 = new HomeItem(R.drawable.shizituandu, TEACHER_LIST, R.id.action_nav_home_to_nav_teacher_list);
        itemList.add(item1);
        HomeItem item2 = new HomeItem(R.drawable.viewlist, LABORATORY_LIST, R.id.action_nav_home_to_nav_laboratory);
        itemList.add(item2);
        HomeItem item3 = new HomeItem(R.drawable.suggest, OTHER_REPORT, R.id.action_nav_home_to_nav_other_report);
        itemList.add(item3);
    }

    // 学生首页
    public static void initHomeItemStudent() {

        itemList.clear();
        HomeItem item1 = new HomeItem(R.drawable.shizituandu, TEACHER_LIST, R.id.action_nav_home_to_nav_teacher_list);
        itemList.add(item1);
        HomeItem item2 = new HomeItem(R.drawable.viewlist, LABORATORY_LIST, R.id.action_nav_home_to_nav_laboratory);
        itemList.add(item2);
        HomeItem item3 = new HomeItem(R.drawable.suggest, OTHER_REPORT, R.id.action_nav_home_to_nav_other_report);
        itemList.add(item3);
        HomeItem item4 = new HomeItem(R.drawable.yvyue, EXPERIMENT_RESERVATION, R.id.action_nav_home_to_nav_experiment_re);
        itemList.add(item4);
        HomeItem item5 = new HomeItem(R.drawable.calendar, MY_COURSE, R.id.action_nav_home_to_nav_my_course);
        itemList.add(item5);
        HomeItem item6 = new HomeItem(R.drawable.task_management, MY_RESERVATION, R.id.action_nav_home_to_nav_my_reserve);
        itemList.add(item6);
    }

    // 教师首页
    public static void initHomeItemTeacher() {

        itemList.clear();
        HomeItem item1 = new HomeItem(R.drawable.shizituandu, TEACHER_LIST, R.id.action_nav_home_to_nav_teacher_list);
        itemList.add(item1);
        HomeItem item2 = new HomeItem(R.drawable.viewlist, LABORATORY_LIST, R.id.action_nav_home_to_nav_laboratory);
        itemList.add(item2);
        HomeItem item3 = new HomeItem(R.drawable.suggest, OTHER_REPORT, R.id.action_nav_home_to_nav_other_report);
        itemList.add(item3);
        HomeItem item4 = new HomeItem(R.drawable.yvyue, RATIFY_EXPERIMENT_RESERVATION, R.id.action_nav_home_to_nav_ratify_exper_re);
        itemList.add(item4);
        HomeItem item5 = new HomeItem(R.drawable.shiyanshishenqing, APPLY_LABORATORY, R.id.action_nav_home_to_nav_lab_apply);
        itemList.add(item5);
        HomeItem item6 = new HomeItem(R.drawable.pcm, APPLY_MATERIAL, R.id.action_nav_home_to_nav_material_apply);
        itemList.add(item6);
        HomeItem item7 = new HomeItem(R.drawable.soft1, APPLY_SOFTWARE, R.id.action_nav_home_to_nav_soft_apply);
        itemList.add(item7);
    }

    // 管理员首页
    public static void initHomeItemAdmin() {

        itemList.clear();
        HomeItem item1 = new HomeItem(R.drawable.shizituandu, TEACHER_LIST, R.id.action_nav_home_to_nav_teacher_list);
        itemList.add(item1);
        HomeItem item2 = new HomeItem(R.drawable.viewlist, LABORATORY_LIST, R.id.action_nav_home_to_nav_laboratory);
        itemList.add(item2);
        HomeItem item3 = new HomeItem(R.drawable.suggest, OTHER_REPORT, R.id.action_nav_home_to_nav_other_report);
        itemList.add(item3);
        HomeItem item4 = new HomeItem(R.drawable.shiyanshishenqing, RATIFY_LABORATORY_APPLY, R.id.action_nav_home_to_nav_ratify_lab_apply);
        itemList.add(item4);
        HomeItem item5 = new HomeItem(R.drawable.pcm, RATIFY_MATERIAL_APPLY, R.id.action_nav_home_to_nav_ratify_ma_apply);
        itemList.add(item5);
        HomeItem item6 = new HomeItem(R.drawable.soft1, RATIFY_SOFTWARE_APPLY, R.id.action_nav_home_to_nav_ratify_so_apply);
        itemList.add(item6);
        HomeItem item7 = new HomeItem(R.drawable.repair, EQUIPMENT_REPAIR_MANAGE, R.id.action_nav_home_to_nav_equip_rep_manage);
        itemList.add(item7);
        HomeItem item8 = new HomeItem(R.drawable.folder, EQUIPMENT_INTO_MANAGE, R.id.action_nav_home_to_nav_equip_into);
        itemList.add(item8);
        HomeItem item9 = new HomeItem(R.drawable.goout, EQUIPMENT_GOTO_MANAGE, R.id.action_nav_home_to_nav_equip_goto);
        itemList.add(item9);
        HomeItem item10 = new HomeItem(R.drawable.material_num, MATERIAL_NUMBER, R.id.action_nav_home_to_nav_material_num);
        itemList.add(item10);
        HomeItem item11 = new HomeItem(R.drawable.notice, ANNOUNCEMENT, R.id.action_nav_home_to_nav_announcement);
        itemList.add(item11);
        HomeItem item12 = new HomeItem(R.drawable.print, STATISTICS_PRINTING, R.id.action_nav_home_to_nav_printing);
        itemList.add(item12);
    }

}
