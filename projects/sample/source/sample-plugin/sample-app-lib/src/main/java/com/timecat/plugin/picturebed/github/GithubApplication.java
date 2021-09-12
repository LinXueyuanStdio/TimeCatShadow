package com.timecat.plugin.picturebed.github;

import android.app.Application;
import android.util.Log;

import com.tencent.mmkv.MMKV;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/12
 * @description null
 * @usage null
 */
public class GithubApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Github", "load PictureBed begin");
        MMKV.initialize(getApplicationContext());
        Log.e("Github", "load PictureBed end");
    }
}
