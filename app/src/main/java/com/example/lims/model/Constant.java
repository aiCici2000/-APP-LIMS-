package com.example.lims.model;

import com.example.lims.R;

import java.util.ArrayList;
import java.util.List;

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

    public static final String HOME = "首页";
    public static final String MESSAGE = "消息";
    public static final String SETTING = "设置";

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

    public static final int HAVE_DATA = 0;
    public static final int NO_DATA = 1;
    public static final int TITLE = 2;

    public static final List<HomeItem> itemList = new ArrayList<>();

    public static void initHomeItemStudent() {

        itemList.clear();
        HomeItem item1 = new HomeItem(R.drawable.shizituandu, TEACHER_LIST, R.id.action_nav_home_to_nav_teacher_list);
        itemList.add(item1);
        HomeItem item2 = new HomeItem(R.drawable.viewlist, LABORATORY_LIST,R.id.action_nav_home_to_nav_laboratory);
        itemList.add(item2);
        HomeItem item3 = new HomeItem(R.drawable.suggest, OTHER_REPORT,R.id.action_nav_home_to_nav_other_report);
        itemList.add(item3);
        HomeItem item4 = new HomeItem(R.drawable.yvyue, EXPERIMENT_RESERVATION,R.id.action_nav_home_to_nav_experiment_re);
        itemList.add(item4);
    }

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
