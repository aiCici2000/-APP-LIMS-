package com.example.lims.view;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static com.example.lims.utils.Constant.ALL_COURSE;
import static com.example.lims.utils.Constant.ANNOUNCEMENT;
import static com.example.lims.utils.Constant.EQUIPMENT_GOTO_MANAGE;
import static com.example.lims.utils.Constant.EQUIPMENT_INTO_MANAGE;
import static com.example.lims.utils.Constant.EQUIPMENT_REPAIR_MANAGE;
import static com.example.lims.utils.Constant.HOME;
import static com.example.lims.utils.Constant.LABORATORY_LIST;
import static com.example.lims.utils.Constant.MATERIAL_NUMBER;
import static com.example.lims.utils.Constant.MESSAGE;
import static com.example.lims.utils.Constant.OTHER_REPORT;
import static com.example.lims.utils.Constant.RATIFY_LABORATORY_APPLY;
import static com.example.lims.utils.Constant.RATIFY_MATERIAL_APPLY;
import static com.example.lims.utils.Constant.RATIFY_SOFTWARE_APPLY;
import static com.example.lims.utils.Constant.SETTING;
import static com.example.lims.utils.Constant.STATISTICS_PRINTING;
import static com.example.lims.utils.Constant.TEACHER_LIST;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.lims.R;
import com.example.lims.databinding.ActivityMainBinding;
import com.example.lims.viewmodel.LoginViewModel;
import com.example.lims.widget.MyToast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/1/4 11:22
 * Describe:
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public ActivityMainBinding mViewBinding;
    public MyToast myToast;
    private LoginViewModel viewModel;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode
                (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mViewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mViewBinding.toolbar.setTitle("");
        setSupportActionBar(mViewBinding.toolbar);
        // 强制竖屏
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        setContentView(mViewBinding.getRoot());

        // 白色字体沉浸
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(getColor(R.color.bottom_down));
        }

        myToast = new MyToast(this, "加载中...");
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        initNav();
    }

    private void initNav() {

        // 获取 NavController
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //TODO FragmentContainerView 获取 NavController时有坑
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {

                switch (navDestination.getId()) {
                    case R.id.nav_home:
                        mViewBinding.toolbarTitle.setText(HOME);
                        mViewBinding.bottomNav.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nav_message:
                        mViewBinding.toolbarTitle.setText(MESSAGE);
                        mViewBinding.bottomNav.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nav_setting:
                        mViewBinding.toolbarTitle.setText(SETTING);
                        mViewBinding.bottomNav.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nav_laboratory:
                        mViewBinding.toolbarTitle.setText(LABORATORY_LIST);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_teacher_list:
                        mViewBinding.toolbarTitle.setText(TEACHER_LIST);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_other_report:
                        mViewBinding.toolbarTitle.setText(OTHER_REPORT);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_ratify_lab_apply:
                        mViewBinding.toolbarTitle.setText(RATIFY_LABORATORY_APPLY);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_ratify_ma_apply:
                        mViewBinding.toolbarTitle.setText(RATIFY_MATERIAL_APPLY);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_ratify_so_apply:
                        mViewBinding.toolbarTitle.setText(RATIFY_SOFTWARE_APPLY);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_equip_rep_manage:
                        mViewBinding.toolbarTitle.setText(EQUIPMENT_REPAIR_MANAGE);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_equip_into:
                        mViewBinding.toolbarTitle.setText(EQUIPMENT_INTO_MANAGE);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_equip_goto:
                        mViewBinding.toolbarTitle.setText(EQUIPMENT_GOTO_MANAGE);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_material_num:
                        mViewBinding.toolbarTitle.setText(MATERIAL_NUMBER);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_announcement:
                        mViewBinding.toolbarTitle.setText(ANNOUNCEMENT);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_printing:
                        mViewBinding.toolbarTitle.setText(STATISTICS_PRINTING);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_all_course:
                        mViewBinding.toolbarTitle.setText(ALL_COURSE);
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_login:
                        mViewBinding.toolbarTitle.setText("");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_change_password:
                        mViewBinding.toolbarTitle.setText("");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_change_email:
                        mViewBinding.toolbarTitle.setText("");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_about:
                        mViewBinding.toolbarTitle.setText("关于");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_message_item:
                        mViewBinding.toolbarTitle.setText("消息");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_ratify_exper_re:
                        mViewBinding.toolbarTitle.setText("审批实验预约");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_lab_apply:
                        mViewBinding.toolbarTitle.setText("申请实验室");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_material_apply:
                        mViewBinding.toolbarTitle.setText("申请材料");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_soft_apply:
                        mViewBinding.toolbarTitle.setText("申请设备软件");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_lab_apply_2:
                        mViewBinding.toolbarTitle.setText("申请实验室");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_add_material:
                        mViewBinding.toolbarTitle.setText("添加耗材");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_laboratory_2:
                        mViewBinding.toolbarTitle.setText("实验室详情");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_laboratory_3:
                        mViewBinding.toolbarTitle.setText("设备报修");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_experiment_re:
                        mViewBinding.toolbarTitle.setText("实验预约");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_my_course:
                        mViewBinding.toolbarTitle.setText("我的课程");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.nav_my_reserve:
                        mViewBinding.toolbarTitle.setText("我的预约");
                        mViewBinding.bottomNav.setVisibility(View.GONE);
                        break;
                }
            }
        });
        // 获取 BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        // 将底部导航绑定 Navigate
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        clearToast(bottomNavigationView);
    }

    // 在返回页面时，关闭 加载中 Toast
    @Override
    public void onBackPressed() {
        if (myToast != null) {
            myToast.closeToast();
        }
        super.onBackPressed();
    }

    // 取消底部导航长按产生的 toast
    public void clearToast(BottomNavigationView bottomNavigationView) {
        ViewGroup bottomNavigationMenuView = (ViewGroup) bottomNavigationView.getChildAt(0);
        List<Integer> ids = Arrays.asList(R.id.nav_home, R.id.nav_message, R.id.nav_setting);
        for (int position = 0; position < ids.size(); position++) {
            bottomNavigationMenuView.getChildAt(position).findViewById(ids.get(position)).setOnLongClickListener(v -> true);
        }
    }

    // 实现点击空白处收起系统软键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //如果是点击事件，获取点击的view，并判断是否要收起键盘
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //获取目前得到焦点的view
            View v = getCurrentFocus();
            Log.d(TAG, "dispatchTouchEvent: "+ v);
            //判断是否要收起并进行处理
            if (v != null) {
                if (isShouldHideKeyboard(v, ev)) {
                    hideKeyboard(v.getWindowToken());
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    //判断是否要收起键盘
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        //如果目前得到焦点的这个view是editText的话进行判断点击的位置
        if (v instanceof EditText) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0];
            int top = l[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            // 点击EditText的事件，忽略它。
            return !(event.getX() > left) || !(event.getX() < right)
                    || !(event.getY() > top) || !(event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上
        return true;
    }

    //隐藏软键盘并让editText失去焦点
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            //这里先获取InputMethodManager再调用他的方法来关闭软键盘
            //InputMethodManager就是一个管理窗口输入的manager
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public boolean getLoginFlag() {
        return Boolean.TRUE.equals(viewModel.getIsLogin().getValue());
    }

    public void setLoginFlag(boolean loginFlag) {
        viewModel.getIsLogin().postValue(loginFlag);
    }
}