package com.timecat.plugin.window;

import android.os.Build;
import android.view.WindowManager;

public class Utils {
    public static boolean isSet(int flags, int flag) {
        return (flags & flag) == flag;
    }

    public static int getWindowType() {
        int type;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        return type;
    }
}
