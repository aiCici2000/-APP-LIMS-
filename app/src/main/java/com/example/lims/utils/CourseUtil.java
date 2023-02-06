package com.example.lims.utils;

/**
 * @Author：李壮
 * @Package：com.example.lims.utils
 * @Date：2023/1/17 16:37
 * Describe:
 */
public class CourseUtil {

    public static String getCourseNum(int i) {
        switch (i) {
            case 1:
                return "第1、2节";
            case 2:
                return "第3、4节";
            case 3:
                return "第5、6节";
            case 4:
                return "第7、8节";
            case 5:
                return "第9、10节";
            case 6:
                return "第11、12节";
            default:
                return "出错了";
        }
    }

}
