package com.example.lims.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

public class CountDownTimerUtils extends CountDownTimer {
	private CountDownTimerListener mCountDownTimerListener;
 
	private TextView mTextView;
 
 
	public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		this.mTextView = textView;
	}
	
 
	public CountDownTimerListener getmCountDownTimerListener() {
		return mCountDownTimerListener;
	}
 
 
	public void setmCountDownTimerListener(CountDownTimerListener mCountDownTimerListener) {
		this.mCountDownTimerListener = mCountDownTimerListener;
	}
 
 
	@Override
	public void onTick(long millisUntilFinished) {
		mTextView.setClickable(false); // 设置不可点击
		mTextView.setText(millisUntilFinished / 1000 + " 秒"); // 设置倒计时时间
		mTextView.setTextColor(Color.GRAY); // 设置按钮为灰色，这时是不能点击的
 
	}
 
	@Override
	public void onFinish() {
		mTextView.setText("重发验证码");
		mTextView.setClickable(true);// 重新获得点击
		mTextView.setTextColor(Color.parseColor("#13227a")); // 还原背景色
	}

	public interface CountDownTimerListener {
		public void onFinishedTimerCount(boolean success);
	}
 
}