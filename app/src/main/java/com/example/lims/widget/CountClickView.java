package com.example.lims.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.databinding.LayoutCountClickViewBinding;
import com.example.lims.utils.ToastUtils;
import com.example.lims.utils.Utils;

import org.jetbrains.annotations.Contract;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/1/27 22:05
 * Describe: 加减Viw，最大支持10000，最小支持 0
 */
public class CountClickView extends LinearLayout {
    private static final String TAG = "CountClickView";

    public static final int MIN_COUNT = 0;
    public static final int INIT_COUNT = 1;
    public static final int MAX_COUNT = 100;

    TextView tvCount;
    ImageView ivPlus;
    ImageView ivMinus;
    /**
     * 减 控件父类
     */
    LinearLayout llMinus;
    /**
     * 加 控件父类
     */
    LinearLayout llPlus;

    private int maxCount = MAX_COUNT;
    private int minCount = MIN_COUNT;
    private int currentCount = INIT_COUNT;
    private int textColor = Color.BLACK;
    private int textSize = 14;

    private LayoutCountClickViewBinding binding;

    /**
     * 是否支持如输入 默认不支持
     */
    private boolean input = false;

    private OnClickAfterListener afterClickListener = null;

    public CountClickView(Context context) {
        this(context, null);
    }

    public CountClickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CountClickView);
        maxCount = a.getInt(R.styleable.CountClickView_ccvMax, maxCount);
        minCount = a.getInt(R.styleable.CountClickView_ccvMin, minCount);
        currentCount = a.getInt(R.styleable.CountClickView_ccvCurrent, currentCount);
        input = a.getBoolean(R.styleable.CountClickView_ccvInput, input);
        textColor = a.getColor(R.styleable.CountClickView_ccvTextColor, textColor);
        textSize = a.getDimensionPixelSize(R.styleable.EnhanceEditText_eTextSize, textSize);
        a.recycle();
        if (isInEditMode()) {
            return;
        }
        binding = LayoutCountClickViewBinding.inflate(LayoutInflater.from(MyApplication.getContext()), this, true);
        bindView();
        init();
    }

    private void bindView() {
        tvCount = binding.tvCount;
        ivPlus = binding.ivPlus;
        ivMinus = binding.ivMinus;
        llPlus = binding.llPlus;
        llMinus = binding.llMinus;
    }

    private void init() {
        setTextColor(textColor);
        setTextSize(textSize);
        setCurrCount(currentCount);
        setClick();
    }

    private void setClick() {
        tvCount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input) {
                    View v = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.dialog_view, null);
                    final EditText editText =  v.findViewById(R.id.dialog_edit);
                    editText.setText(tvCount.getText());
                    AlertDialog dialog = new AlertDialog.Builder(getContext())
                            .setTitle("请输入数量")
                            .setView(v)
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String content = editText.getText().toString();
                                    Log.d(TAG, "onClick---dialog1: " + content);
                                    if (!content.equals("")) {
                                        if (Utils.isNumber(content)) {
                                            int count = Integer.parseInt(content);
                                            if (count > MAX_COUNT) {
                                                count = maxCount;
                                                ToastUtils.show("超过了设置的最大值");
                                            } else if (count < MIN_COUNT) {
                                                count = MIN_COUNT;
                                                ToastUtils.show("超过了设置的最小值");
                                            }
                                            Log.d(TAG, "onClick---dialog2: "+ count);
                                            setCurrCount(count);
                                        }
                                    }
                                    dialogInterface.dismiss();
                                }
                            }).create();
                    dialog.show();
                }
            }
        });
        llMinus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentCount > getMinCount()) {
                    tvCount.setText(String.valueOf(--currentCount));
                }
                judgeTheViews(currentCount);
            }
        });
        llPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentCount < getMaxCount()) {
                    tvCount.setText(String.valueOf(++currentCount));
                }
                judgeTheViews(currentCount);
            }
        });
    }

    /**
     * 根据加减 设置显示的控件View
     *
     * @param count 数量
     */
    private void judgeTheViews(int count) {
        Log.d(TAG, "judgeTheViews4: " + count);
        ivMinus.setEnabled(count > getMinCount());
        ivPlus.setEnabled(count < getMaxCount());
    }

    /**
     * 判断最大值
     *
     * @return 返回最大值
     */
    @Contract(pure = true)
    private int getMaxCount() {
        return Math.min(maxCount, MAX_COUNT);
    }

    /**
     * 判断最小值
     *
     * @return 返回最小值
     */
    @Contract(pure = true)
    private int getMinCount() {
        return Math.max(minCount, MIN_COUNT);
    }

    /**
     * 设置文字颜色
     *
     * @param textColor 颜色
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
        tvCount.setTextColor(textColor);
    }

    /**
     * 设置文字大小
     *
     * @param textSize 大小
     */
    public void setTextSize(int textSize) {
        this.textSize = textSize;
        tvCount.setTextSize(this.textSize);
    }

    /**
     * 设置默认显示的数量
     *
     * @param count 数量
     */
    public void setCurrCount(int count) {
        Log.d(TAG, "setCurrCount3: " + count);
        currentCount = count;
        tvCount.setText(String.valueOf(currentCount));
        judgeTheViews(count);
    }

    /**
     * 返回操作后的数量
     *
     * @return 数量
     */
    public int getCount() {
        String text = tvCount.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            return INIT_COUNT;
        }
        return Integer.parseInt(text);
    }

    /**
     * 操作之后监听回调
     */
    public interface OnClickAfterListener {

        /**
         * 操作之后
         *
         * @param view  CountClickView
         * @param value 值
         */
        void onAfter(CountClickView view, int value);
    }
}
