package com.tencent.shadow.sample.plugin.app.lib.usecases.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.tencent.shadow.sample.plugin.app.lib.gallery.util.ToastUtil;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/14
 * @description null
 * @usage null
 */
public abstract class ATestService extends Service {

    public abstract void test();

}
