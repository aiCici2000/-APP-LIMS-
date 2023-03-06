package com.example.lims.view;

import static com.example.lims.viewmodel.ApplyViewModel.STUDENT_RESERVATION;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.example.lims.adapter.CourseSimpleAdapter;
import com.example.lims.adapter.TeacherAdapter;
import com.example.lims.databinding.FragmentExperimentRe2Binding;
import com.example.lims.model.bean.CourseNumberData;
import com.example.lims.model.bean.Reservation;
import com.example.lims.model.bean.UserData;
import com.example.lims.utils.DateUtil;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.net.RetrofitService;
import com.example.lims.utils.net.RetrofitUtil;
import com.example.lims.view.base.BaseDialogFragment;
import com.example.lims.viewmodel.TeacherViewModel;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author：李壮
 * @Package：com.example.lims.view
 * @Date：2023/2/26 15:59
 * Describe:  实验预约 二级页面
 */
public class ExperimentReFragment2 extends BaseDialogFragment<FragmentExperimentRe2Binding> implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnCalendarInterceptListener,
        CalendarView.OnYearChangeListener {

    private static final String TAG = "ExperimentReFragment2";

    public static final int FAIL = 0;
    public static final int SUCCESS = 1;
    public static final int TO_BE_REVIEWED = 2;

    private FragmentExperimentRe2Binding binding;
    private final List<CourseNumberData.DataBean> list = new ArrayList<>();
    private final List<UserData.DataBean> teacherList = new ArrayList<>();
    private CourseSimpleAdapter adapter;
    private TeacherAdapter teacherAdapter;
    private CourseNumberData.DataBean chooseItem;
    private UserData.DataBean teacherItem;
    private String selectDate;
    private TeacherViewModel viewModel;
    private final List<Integer> courseInfo = new ArrayList<>();
    private Call<UserData> teacher;

    TextView mTextMonthDay;
    TextView mTextYear;
    TextView mTextLunar;
    TextView mTextCurrentDay;
    CalendarView mCalendarView;
    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;
    int labId;
    int administratorId;
    private String labName;
    private String labNumber;
    private String courseName;

    @Override
    protected ViewBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentExperimentRe2Binding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    public void init() {
        Bundle bundle = getArguments();
        labName = bundle.getString("lab_name");
        labNumber = bundle.getString("lab_number");
        binding.tv2.setText(labName);
        binding.tv3.setText(labNumber);
        labId = bundle.getInt("lab_id");
        administratorId = bundle.getInt("administratorId");
        viewModel = new ViewModelProvider(this).get(TeacherViewModel.class);
        initView();
        initData();
        initRecycleView();
        initObserver();
        networkRequest();
    }

    @Override
    public void initEvent() {
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teacherItem == null) {
                    ToastUtils.show("请选择老师！");
                } else {
                    Reservation reservation = new Reservation();
                    reservation.setType(STUDENT_RESERVATION);
                    reservation.setApplicantId(userId);
                    reservation.setApplicantName(userName);
                    reservation.setCourseNumber(chooseItem.getCourseNumber());
                    reservation.setCourseTime(selectDate);
                    reservation.setStatus(TO_BE_REVIEWED);
                    reservation.setTeacherId(teacherItem.getId());
                    reservation.setLaboratoryId(labId);
                    reservation.setLabName(labName);
                    reservation.setLabNumber(labNumber);
                    reservation.setCourseName(courseName);
                    reservation.setTime(DateUtil.getTime());
                    viewModel.setResultInterface(new TeacherViewModel.ResultInterface() {
                        @Override
                        public void help() {
                            NavController controller = Navigation.findNavController(requireView());
                            controller.popBackStack();
                        }
                    });
                    viewModel.addReservation(reservation);
                }
            }
        });
        binding.selectTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseName = binding.et.getText() + "";
                if (chooseItem == null) {
                    ToastUtils.show("请选择节次！");
                } else if (TextUtils.isEmpty(courseName)) {
                    ToastUtils.show("请输入课程名！");
                } else {
                    binding.group.setVisibility(View.GONE);
                    binding.group1.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    protected void initView() {
//        setStatusBarDarkMode();
        mTextMonthDay = binding.tvMonthDay;
        mTextYear = binding.tvYear;
        mTextLunar = binding.tvLunar;
        mRelativeTool = binding.rlTool;
        mCalendarView = binding.calendarView;
        mTextCurrentDay = binding.tvCurrentDay;
        binding.flCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });
        mCalendarLayout = binding.calendarLayout;
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);

        mCalendarView.setOnCalendarInterceptListener(this);

        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));

        mCalendarView.scrollToCurrent();
        selectDate = getSelectDate(mCalendarView.getCurYear(), mCalendarView.getCurMonth(), mCalendarView.getCurDay());

        int curMonth = mCalendarView.getCurMonth();
        if (curMonth == 12) {
            mCalendarView.setRange(mCalendarView.getCurYear(), mCalendarView.getCurMonth(), 1, mCalendarView.getCurYear() + 1, mCalendarView.getCurMonth() + 1, 31);
        } else {
            mCalendarView.setRange(mCalendarView.getCurYear(), mCalendarView.getCurMonth(), 1, mCalendarView.getCurYear(), mCalendarView.getCurMonth() + 1, 31);
        }
    }

    protected void initData() {
        Map<String, Calendar> map = new HashMap<>();
        viewModel.getCourseInfo().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
                courseInfo.clear();
                courseInfo.addAll(integers);
                Log.d(TAG, "onChanged---: " + courseInfo.toString());
                for (int i = 0; i < courseInfo.size(); i++) {
                    Date date = DateUtil.addDay(new Date(), i);
                    int year = DateUtil.getYear(date);
                    int month = DateUtil.getMonth(date);
                    int day = DateUtil.getDay(date);
                    Log.d(TAG, String.format("date: year %d , month %d , day %d  ", year, month, day));
                    int temp = courseInfo.get(i) * 20;
                    map.put(getSchemeCalendar(year, month + 1, day, 0xFFe69138, String.valueOf(temp)).toString(),
                            getSchemeCalendar(year, month + 1, day, 0xFFe69138, String.valueOf(temp)));
                }
                Log.d(TAG, "onChang" + map.toString());
//                mCalendarView.setSchemeDate(map);
            }
        });
        viewModel.requestCourseInfo(STUDENT_RESERVATION, labId, getDateNow(), SUCCESS);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    /**
     * 屏蔽某些不可点击的日期，可根据自己的业务自行修改
     * 如 calendar > 2018年1月1日 && calendar <= 2020年12月31日
     *
     * @param calendar calendar
     * @return 是否屏蔽某些不可点击的日期，MonthView和WeekView有类似的API可调用
     */
    @Override
    public boolean onCalendarIntercept(Calendar calendar) {
        return calendar.getYear() <= mCalendarView.getCurYear() &&
                calendar.getMonth() <= mCalendarView.getCurMonth() &&
                calendar.getDay() < mCalendarView.getCurDay();
    }

    @Override
    public void onCalendarInterceptClick(Calendar calendar, boolean isClick) {

    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }

    private void initObserver() {
        viewModel.getCourseNumberList().observe(this, new Observer<List<CourseNumberData.DataBean>>() {
            @Override
            public void onChanged(List<CourseNumberData.DataBean> dataBeans) {
                list.clear();
                list.addAll(dataBeans);
                adapter.notifyDataSetChanged();
            }
        });


        mCalendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                int year = calendar.getYear();
                int month = calendar.getMonth();
                int day = calendar.getDay();
                viewModel.requestCourseNumber(STUDENT_RESERVATION, labId, getSelectDate(year, month, day), SUCCESS);
                //TODO new新的Adapter
                adapter = new CourseSimpleAdapter(list, false);
                adapter.setListener(new CourseSimpleAdapter.ItemOnClickListener() {
                    @Override
                    public void help(int position) {
                        chooseItem = list.get(position);
                    }
                });
                binding.rv.setAdapter(adapter);
            }
        });
    }

    private void initRecycleView() {
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 3);
        binding.rv.setLayoutManager(layoutManager);
        adapter = new CourseSimpleAdapter(list, false);
        adapter.setListener(new CourseSimpleAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                chooseItem = list.get(position);
            }
        });
        binding.rv.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        binding.rvTeacher.setLayoutManager(manager);
        teacherAdapter = new TeacherAdapter(teacherList);
        teacherAdapter.setListener(new TeacherAdapter.ItemOnClickListener() {
            @Override
            public void help(int position) {
                teacherItem = teacherList.get(position);

            }
        });
        binding.rvTeacher.setAdapter(teacherAdapter);
    }

    private void networkRequest() {
        RetrofitService service = RetrofitUtil.getRetrofit().create(RetrofitService.class);
        teacher = service.getTeacherList(1);
        teacher.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData userData = response.body();
                try {
                    List<UserData.DataBean> dataBeans = userData.getData();
                    teacherList.clear();
                    teacherList.addAll(dataBeans);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d(TAG, "onFailure: 请求失败！");
            }
        });
    }

    private String getDateNow() {
        int curDay = mCalendarView.getCurDay();
        int curMonth = mCalendarView.getCurMonth();
        int curYear = mCalendarView.getCurYear();
        return curYear + "-" + curMonth + "-" + curDay + " " + "00:00:00";
    }

    private String getSelectDate(int year, int month, int day) {
        selectDate = year + "-" + month + "-" + day + " " + "00:00:00";
        return selectDate;
    }

}
