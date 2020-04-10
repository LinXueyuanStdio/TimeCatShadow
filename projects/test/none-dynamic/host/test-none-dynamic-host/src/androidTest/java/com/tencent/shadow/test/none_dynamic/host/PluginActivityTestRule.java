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

package com.tencent.shadow.test.none_dynamic.host;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.intercepting.SingleActivityFactory;

import com.tencent.shadow.core.loader.ShadowPluginLoader;
import com.tencent.shadow.core.loader.managers.ComponentManager;

public class PluginActivityTestRule<T extends Activity> extends ActivityTestRule<T> {
    final private Intent mStartIntent;

    public static ActivityTestRule<?> build(Intent pluginIntent) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        Context applicationContext = targetContext.getApplicationContext();
        HostApplication application = (HostApplication) applicationContext;
        ShadowPluginLoader pluginLoader = application.getPluginLoader();
        ComponentManager componentManager = pluginLoader.getComponentManager();

        Intent intent = componentManager.convertPluginActivityIntent(pluginIntent);
        if (intent == pluginIntent) {
            throw new RuntimeException("插件中到不到" + pluginIntent.getComponent());
        }

        String containerActivityClassName = intent.getComponent().getClassName();
        Class<? extends Activity> containerActivityClass;
        try {
            Class<?> aClass = Class.forName(containerActivityClassName);
            containerActivityClass = aClass.asSubclass(Activity.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new PluginActivityTestRule<>(containerActivityClass, intent);
    }

    public PluginActivityTestRule(Class<T> activityClass, Intent mStartIntent) {
        super(activityClass);
        this.mStartIntent = mStartIntent;
    }

    public PluginActivityTestRule(Class<T> activityClass, boolean initialTouchMode, Intent mStartIntent) {
        super(activityClass, initialTouchMode);
        this.mStartIntent = mStartIntent;
    }

    public PluginActivityTestRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity, Intent mStartIntent) {
        super(activityClass, initialTouchMode, launchActivity);
        this.mStartIntent = mStartIntent;
    }

    public PluginActivityTestRule(SingleActivityFactory<T> activityFactory, boolean initialTouchMode, boolean launchActivity, Intent mStartIntent) {
        super(activityFactory, initialTouchMode, launchActivity);
        this.mStartIntent = mStartIntent;
    }

    public PluginActivityTestRule(Class<T> activityClass, @NonNull String targetPackage, int launchFlags, boolean initialTouchMode, boolean launchActivity, Intent mStartIntent) {
        super(activityClass, targetPackage, launchFlags, initialTouchMode, launchActivity);
        this.mStartIntent = mStartIntent;
    }

    @Override
    protected Intent getActivityIntent() {
        return mStartIntent;
    }
}
