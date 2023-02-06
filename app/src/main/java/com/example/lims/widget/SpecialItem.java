package com.example.lims.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.lims.MyApplication;
import com.example.lims.R;
import com.example.lims.databinding.LayoutSpecialItemBinding;
import com.example.lims.utils.PxUtils;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author：李壮
 * @Package：com.example.lims.widget
 * @Date：2023/1/5 14:05
 * Describe: 多功能 Item
 */
public class SpecialItem extends LinearLayout {

    private LayoutSpecialItemBinding mViewBinding;

    LinearLayout rlLayout;
    ImageView ivLeft;
    TextView tvLeft;
    ImageView ivRight;
    TextView tvRight;
    EditText edtText;
    LinearLayout layout;
    LinearLayout content;
    View topLine;
    View bottomLine;

    private int leftTextColor = Color.BLACK;
    private int rightTextColor = Color.GRAY;
    private int leftIconResource = R.drawable.xiaohui_s;
    private int rightIconResource = R.drawable.icon_more;
    private String leftText = "";
    private String rightTex = "";
    private boolean showTopLine = false;
    private boolean showBottomLine = true;
    private boolean showLeftIcon = true;
    private boolean showRightIcon = true;
    private boolean showEditText = false;

    private OnIconCheckChangedListener leftIconChangedListener, rightIconChangedListener;

    public SpecialItem(Context context) {
        this(context, null);
    }

    public SpecialItem(Context context, @Nullable AttributeSet attr) {
        this(context, attr, 0);
    }

    public SpecialItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SpecialItem);
        leftIconResource = array.getResourceId(R.styleable.SpecialItem_leftIconResource, leftIconResource);
        leftText = array.getString(R.styleable.SpecialItem_leftText);
        array.recycle();
        if (isInEditMode()) {
            return;
        }
        mViewBinding = LayoutSpecialItemBinding.inflate(LayoutInflater.from(MyApplication.getContext()), this, true);
        bindView(mViewBinding);
        init();
    }

    private void init() {

        setLeftIconResource(leftIconResource);
        setRightIconResource(rightIconResource);
        setLeftText(leftText);
        setRightText(rightTex);
        setShowLeftIcon(showLeftIcon);
        setShowRightIcon(showRightIcon);
        setShowTopLine(showTopLine);
        setShowBottomLine(showBottomLine);
        setShowEditText(showEditText);
        tvLeft.setTextColor(leftTextColor);
        edtText.setTextColor(rightTextColor);
        tvRight.setTextColor(rightTextColor);

        //如果手动设置了背景 则赋值给layout 否则设置默认颜色
        Drawable background = getBackground();
        if (background != null) {
            rlLayout.setBackground(background);
        } else {
            rlLayout.setBackgroundColor(Color.WHITE);
        }
    }

    /**
     * 左右按钮 选择状态变化
     */
    public interface OnIconCheckChangedListener {
        /**
         * 按钮选择状态变化
         *
         * @param checked 选择状态
         */
        void onIconChanged(boolean checked);
    }

    // 绑定 View
    private void bindView(LayoutSpecialItemBinding mViewBinding) {
        rlLayout = mViewBinding.rlLayout;
        ivLeft = mViewBinding.ivLeft;
        tvLeft = mViewBinding.tvLeft;
        ivRight = mViewBinding.ivRight;
        tvRight = mViewBinding.tvRight;
        edtText = mViewBinding.edtText;
        layout = mViewBinding.llRight;
        content = mViewBinding.rlContent;
        topLine = mViewBinding.lineTop;
        bottomLine = mViewBinding.lineBottom;
    }

    /**
     * 设置是否显示EditText
     *
     * @param show text
     * @return MultifunctionalItemView
     */
    public SpecialItem setShowEditText(boolean show) {
        if (show) {
            edtText.setVisibility(VISIBLE);
            tvRight.setVisibility(GONE);
        }
        return this;
    }

    /**
     * 设置上面线是否显示
     *
     * @param show text
     * @return MultifunctionalItemView
     */
    public SpecialItem setShowTopLine(boolean show) {
        topLine.setVisibility(show ? VISIBLE : GONE);
        return this;
    }

    /**
     * 设置下面线是否显示
     *
     * @param show text
     * @return MultifunctionalItemView
     */
    public SpecialItem setShowBottomLine(boolean show) {
        bottomLine.setVisibility(show ? VISIBLE : GONE);
        return this;
    }

    /**
     * 设置左边文字
     *
     * @param text text
     * @return MultifunctionalItemView
     */
    public SpecialItem setLeftText(String text) {
        tvLeft.setText(text);
        return this;
    }

    /**
     * 设置左边文字颜色
     *
     * @param color 颜色
     * @return MultifunctionalItemView
     */
    public SpecialItem setLeftTextColorResource(int color) {
        tvLeft.setTextColor(getResources().getColor(color));
        return this;
    }

    /**
     * 设置左边文字大小
     *
     * @param size 大小
     * @return MultifunctionalItemView
     */
    public SpecialItem setLeftTextSize(int size) {
        tvLeft.setTextSize(size);
        return this;
    }

    /**
     * 设置右边文字
     *
     * @param text text
     * @return MultifunctionalItemView
     */
    public SpecialItem setRightText(String text) {
        tvRight.setText(text);
        edtText.setText(text);
        return this;
    }

    /**
     * 设置右边文字颜色
     *
     * @param color 颜色
     * @return MultifunctionalItemView
     */
    public SpecialItem setRightTextColorResource(int color) {
        tvRight.setTextColor(getResources().getColor(color));
        edtText.setTextColor(getResources().getColor(color));
        return this;
    }

    /**
     * 设置右边文字大小
     *
     * @param size 大小
     * @return MultifunctionalItemView
     */
    public SpecialItem setRightTextSize(int size) {
        tvRight.setTextSize(size);
        edtText.setTextScaleX(size);
        return this;
    }

    /**
     * 设置左边Icon Margin
     *
     * @param left   左
     * @param top    上
     * @param right  右
     * @param bottom 下
     * @return MultifunctionalItemView
     */
    public SpecialItem setLeftIconMargin(int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ivLeft.getLayoutParams();
        layoutParams.setMargins(PxUtils.dp2px(left), PxUtils.dp2px(top), PxUtils.dp2px(right), PxUtils.dp2px(bottom));
        return this;
    }

    /**
     * 设置右边Icon Margin
     *
     * @param left   左
     * @param top    上
     * @param right  右
     * @param bottom 下
     * @return MultifunctionalItemView
     */
    public SpecialItem setRightIconMargin(int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ivRight.getLayoutParams();
        layoutParams.setMargins(PxUtils.dp2px(left), PxUtils.dp2px(top),
                PxUtils.dp2px(right), PxUtils.dp2px(bottom));
        return this;
    }


    /**
     * 是否显示左边图标
     *
     * @param show show
     * @return MultifunctionalItemView
     */
    public SpecialItem setShowLeftIcon(boolean show) {
        ivLeft.setVisibility(show ? VISIBLE : GONE);
        return this;
    }

    /**
     * 设置左边图标图片
     *
     * @param resId 资源ID
     * @return MultifunctionalItemView
     */
    public SpecialItem setLeftIconResource(int resId) {
        this.ivLeft.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置左边图标大小
     *
     * @param width  宽
     * @param height 高
     * @return MultifunctionalItemView
     */
    public SpecialItem setLeftIconSize(int width, int height) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(PxUtils.dp2px(width),
                PxUtils.dp2px(height));
        lp.setMargins(0, 0, 4, 0);
        ivLeft.setLayoutParams(lp);
        return this;
    }


    /**
     * 是否显示右边图标
     *
     * @param show show
     * @return MultifunctionalItemView
     */
    public SpecialItem setShowRightIcon(boolean show) {
        ivRight.setVisibility(show ? VISIBLE : GONE);
        return this;
    }

    /**
     * 设置右边图标资源
     *
     * @param resId 资源ID
     * @return MultifunctionalItemView
     */
    public SpecialItem setRightIconResource(int resId) {
        this.ivRight.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置右边图标大小
     *
     * @param width  宽
     * @param height 高
     * @return MultifunctionalItemView
     */
    public SpecialItem setRightIconSize(int width, int height) {
        ivRight.setLayoutParams(new LinearLayout.LayoutParams(PxUtils.dp2px(width),
                PxUtils.dp2px(height)));
        return this;
    }

    /**
     * 设置父窗体 padding
     *
     * @param left   左
     * @param top    上
     * @param right  右
     * @param bottom 下
     * @return MultifunctionalItemView
     */
    public SpecialItem setContentPadding(int left, int top, int right, int bottom) {
        content.setPadding(PxUtils.dp2px(left), PxUtils.dp2px(top),
                PxUtils.dp2px(right), PxUtils.dp2px(bottom));
        return this;
    }

    /**
     * 设置右icon选择
     *
     * @param checked 选择
     * @return this
     */
    public SpecialItem setRightIconChecked(boolean checked) {
        ivRight.setSelected(checked);
        if (leftIconChangedListener != null) {
            leftIconChangedListener.onIconChanged(checked);
        }
        return this;
    }

    /**
     * 设置左icon选择
     *
     * @param checked 选择
     * @return this
     */
    public SpecialItem setLeftIconChecked(boolean checked) {
        ivRight.setSelected(checked);
        if (rightIconChangedListener != null) {
            rightIconChangedListener.onIconChanged(checked);
        }
        return this;
    }

    /**
     * 左侧按钮变化监听
     *
     * @param iconChangedListener 监听器
     * @return this
     */
    public SpecialItem setLeftIconChangedListener(OnIconCheckChangedListener iconChangedListener) {
        this.leftIconChangedListener = iconChangedListener;
        return this;
    }

    /**
     * 右侧按钮变化监听
     *
     * @param iconChangedListener 监听器
     * @return this
     */
    public SpecialItem setRightIconChangedListener(OnIconCheckChangedListener iconChangedListener) {
        this.rightIconChangedListener = iconChangedListener;
        return this;
    }

    /**
     * 设置父窗体 padding
     *
     * @param padding padding
     * @return MultifunctionalItemView
     */
    public SpecialItem setContentPadding(int padding) {
        return setContentPadding(padding, padding, padding, padding);
    }

    /**
     * 设置右边View
     *
     * @param view view
     * @return MultifunctionalItemView
     */
    public SpecialItem setRightView(View view) {
        this.layout.removeAllViews();
        this.layout.addView(view);
        return this;
    }

    /**
     * 当在输入模式下 返回输入框的值
     *
     * @return String
     */
    public String getRightTex() {
        return edtText.getText().toString().trim();
    }


//    @OnClick({R2.id.iv_left, R2.id.iv_right})
//    public void onIconClick(@NotNull View view) {
//        view.setSelected(!view.isSelected());
//        if (view.getId() == R.id.iv_left) {
//            if (leftIconChangedListener != null) {
//                leftIconChangedListener.onIconChanged(view.isSelected());
//            }
//        } else if (view.getId() == R.id.iv_right) {
//            if (rightIconChangedListener != null) {
//                rightIconChangedListener.onIconChanged(view.isSelected());
//            }
//        }
//    }
}
