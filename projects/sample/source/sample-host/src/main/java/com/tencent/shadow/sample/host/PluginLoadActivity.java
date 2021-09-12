/*
 * Tencent is pleased to support the open source community by making Tencent Shadow available.
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.tencent.shadow.sample.host;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.shadow.dynamic.host.EnterCallback;
import com.tencent.shadow.sample.constant.Constant;


public class PluginLoadActivity extends Activity {

    private ViewGroup mViewGroup;

    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        mViewGroup = findViewById(R.id.container);

        startPlugin();
    }


    public void startPlugin() {

        PluginHelper.getInstance().singlePool.execute(new Runnable() {
            @Override
            public void run() {
                HostApplication.getApp().loadPluginManager(PluginHelper.getInstance().pluginManagerFile);

                Bundle extra = new Bundle();
                extra.putInt("id", 100);
                String action = "SHOW";
                Uri uri = null;
                Bundle bundle = getBundle(
                        PluginHelper.getInstance().pluginZipFile.getAbsolutePath(),
                        getIntent().getStringExtra(Constant.KEY_PLUGIN_PART_KEY),
                        getIntent().getStringExtra(Constant.KEY_ACTIVITY_CLASSNAME),//"com.timecat.plugin.picturebed.github.GithubApp",
                        extra, uri, action);
                HostApplication.getApp().getPluginManager()
                               .enter(PluginLoadActivity.this, Constant.FROM_ID_START_ACTIVITY, bundle, new EnterCallback() {
                                   @Override
                                   public void onShowLoadingView(final View view) {
                                       mHandler.post(new Runnable() {
                                           @Override
                                           public void run() {
                                               mViewGroup.addView(view);
                                           }
                                       });
                                   }

                                   @Override
                                   public void onCloseLoadingView() {
                                       finish();
                                   }

                                   @Override
                                   public void onEnterComplete() {

                                   }
                               });
            }
        });
    }

    private Bundle getBundle(String zipAbsPathForPlugin,
            String partName,
            String activity_class_name,
            Bundle extra,
            Uri data,
            String action) {
        Bundle bundle = new Bundle();
        bundle.putString(PluginManagerAggreement.KEY_PLUGIN_ZIP_PATH, zipAbsPathForPlugin);
        bundle.putString(PluginManagerAggreement.KEY_PLUGIN_PART_KEY, partName);
        bundle.putString(PluginManagerAggreement.KEY_ACTIVITY_CLASSNAME, activity_class_name);
        bundle.putBundle(PluginManagerAggreement.KEY_EXTRAS, extra);
        bundle.putString(PluginManagerAggreement.KEY_ACTION, action);
        bundle.putParcelable(PluginManagerAggreement.KEY_DATA, data);
        return bundle;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewGroup.removeAllViews();
    }
}
