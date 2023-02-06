package com.example.lims.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.databinding.LayoutMessageItemBinding;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/1/9 14:52
 * Describe:
 */
public class MessageItem extends ConstraintLayout {
    private static final String TAG = "MessageItem";

    private LayoutMessageItemBinding binding;

    ConstraintLayout layout;
    ImageView iv1;
    TextView tv1;
    TextView tv2;
    ImageView iv2;

    private int imageResource1 = R.color.bottom_down;
    private int imageResource2 = R.color.color_f31515;
    private String text1 = "";
    private String text2 = "";
    private boolean showSpot = true;


    public MessageItem(@NonNull Context context) {
        this(context, null);
    }

    public MessageItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MessageItem);
        imageResource1 = array.getResourceId(R.styleable.MessageItem_imageResource1, imageResource1);
        imageResource2 = array.getResourceId(R.styleable.MessageItem_imageResource2, imageResource2);
        text1 = array.getString(R.styleable.MessageItem_text1);
        text2 = array.getString(R.styleable.MessageItem_text2);
        showSpot = array.getBoolean(R.styleable.MessageItem_showSpot, true);
        array.recycle();
        if (isInEditMode()) {
            Log.d(TAG, "MessageItem: ");
            return;
        }
        binding = LayoutMessageItemBinding.inflate(LayoutInflater.from(MyApplication.getContext()), this, true);
        bindView();
        init();
    }

    private void bindView() {
        layout = binding.miLayout;
        iv1 = binding.image1;
        iv2 = binding.image2;
        tv1 = binding.text1;
        tv2 = binding.text2;
    }

    private void init() {
        setImageResource1(imageResource1);
        setImageResource2(imageResource2);
        setText1(text1);
        setText2(text2);
        setShowSpot(showSpot);
    }

    private MessageItem setImageResource1(int resId) {
        this.iv1.setBackgroundResource(resId);
        return this;
    }

    private MessageItem setImageResource2(int resId) {
        this.iv2.setBackgroundResource(resId);
        return this;
    }

    private MessageItem setText1(String text) {
        this.tv1.setText(text);
        return this;
    }

    private MessageItem setText2(String text) {
        this.tv2.setText(text);
        return this;
    }

    private MessageItem setShowSpot(boolean b) {
        this.iv1.setVisibility(b ? VISIBLE : GONE);
        return this;
    }
}
