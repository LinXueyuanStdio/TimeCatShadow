package com.tencent.shadow.sample.plugin.app.lib.usecases.service;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.tencent.shadow.sample.plugin.app.lib.R;
import com.tencent.shadow.sample.plugin.app.lib.gallery.cases.entity.UseCase;
import com.tencent.shadow.sample.plugin.app.lib.gallery.util.ToastUtil;
import com.timecat.plugin.picturebed.github.MainActivity;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/14
 * @description null
 * @usage null
 */
public class TestServiceActivity extends Activity {
    public static class Case extends UseCase {
        @Override
        public String getName() {
            return "Service 测试";
        }

        @Override
        public String getSummary() {
            return "测试 Activity 里启动一个 Service";
        }

        @Override
        public Class getPageClass() {
            return TestServiceActivity.class;
        }
    }

    private Intent serviceIntent ;

    private TestService.MyLocalServiceBinder binder;

    private TextView mTextView;

    public final static String INTENT_ACTION = "com.tencent.shadow.test.service";

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceIntent = new Intent(this, TestService.class);
        setContentView(R.layout.layout_service);
        mTextView = findViewById(R.id.tv_msg);
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,new IntentFilter(INTENT_ACTION));
    }

    public void start(View view) {
        setIdle();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(serviceIntent);
//        } else {
//        }
            startService(serviceIntent);
    }

    public void bind(View view) {
        setIdle();
        bindService(serviceIntent, serviceConnection, Service.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (TestService.MyLocalServiceBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
        }
    };

    public void stop(View view) {
        setIdle();
        stopService(serviceIntent);
    }

    public void unbind(View view) {
        setIdle();
        unbindService(serviceConnection);
    }

    public void testBinder(View view) {
        setIdle();
        if (binder == null) {
            ToastUtil.showToast(this, "请先bindService");
        } else {
            binder.getMyLocalService().test();
        }
    }

    private void setIdle(){
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mHandler.removeCallbacksAndMessages(null);
            String text = intent.getStringExtra("result");
            String oldText = mTextView.getText().toString();
            if(!TextUtils.isEmpty(oldText)){
                text = oldText+"-"+text;
            }
            mTextView.setText(text);
        }
    };
}
