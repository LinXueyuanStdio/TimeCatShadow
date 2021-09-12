package com.timecat.plugin.picturebed.github.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/12
 * @description null
 * @usage null
 */
public class StatusView extends FrameLayout {
    public StatusView(Context context) {
        super(context);
        init(context);
    }

    public StatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StatusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    ProgressBar progressBar;
    TextView textView;

    private void init(Context context) {
        progressBar = new ProgressBar(context);
        addView(progressBar);
        LayoutParams lp = (LayoutParams) progressBar.getLayoutParams();
        lp.gravity = Gravity.CENTER;

        textView = new TextView(context);
        addView(textView);
        LayoutParams lp2 = (LayoutParams) textView.getLayoutParams();
        lp2.gravity = Gravity.CENTER;
        textView.setTextColor(Color.BLACK);

        onLoading();
    }

    public void onLoading() {
        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void onFail(OnClickListener onClickListener) {
        textView.setVisibility(View.VISIBLE);
        textView.setText("重试");
        textView.setTextColor(Color.RED);
        textView.setOnClickListener(onClickListener);

        progressBar.setVisibility(View.GONE);
    }

    public void onSuccess(String url, OnClickListener onClickListener) {
        textView.setVisibility(View.VISIBLE);
        textView.setText(url);
        textView.setTextColor(Color.BLACK);
        textView.setOnClickListener(onClickListener);
        progressBar.setVisibility(View.GONE);
    }
}
