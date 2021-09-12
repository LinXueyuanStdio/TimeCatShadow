package com.timecat.plugin.picturebed.github;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.tencent.shadow.sample.plugin.app.lib.R;
import com.timecat.plugin.picturebed.github.view.GithubBedView;
import com.timecat.plugin.window.StandOutFlags;
import com.timecat.plugin.window.StandOutWindow;
import com.timecat.plugin.window.Window;
import com.timecat.plugin.window.WindowAgreement;

import java.util.ArrayList;
import java.util.List;

public class GithubApp extends StandOutWindow {
    private Context context;
    private int publicId;
    private View publicView;
    public GithubApp() {
    }
    public String getAppName() {
        return getString(R.string.main_miniapp_Github);
    }

    public int getAppIcon() {
        return R.drawable.ic_window_menu;
    }

    public String getTitle(int id) {
        return getString(R.string.main_miniapp_Github);
    }

    public String getPersistentNotificationTitle(int id) {
        return getString(R.string.main_miniapp_Github);
    }

    public String getPersistentNotificationMessage(int id) {
        return getString(R.string.main_miniapp_running);
    }

    public int getHiddenIcon() {
        return R.drawable.ic_github;
    }

    public String getHiddenNotificationTitle(int id) {
        return getString(R.string.main_miniapp_Github);
    }

    public String getHiddenNotificationMessage(int id) {
        return getString(R.string.main_miniapp_mininized);
    }

    public Intent getHiddenNotificationIntent(int id) {
        return WindowAgreement.getShowIntent(this, getClass(), id);
    }

    public Animation getShowAnimation(int id) {
        if (isExistingId(id)) {
            return AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        }
        return super.getShowAnimation(id);
    }

    public Animation getHideAnimation(int id) {
        return AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
    }

    public static int dp2px(Context context, int pixels) {
        return (int) (((float) pixels) * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    @Override
    public int getThemeStyle() {
        return android.R.style.Theme_Material_NoActionBar;
    }

    public StandOutLayoutParams getParams(int id, Window window) {
        int h = 200;//DEF.githubApp().getInt(getAppName() + "HEIGHT", 200);
        int w = 200;//DEF.githubApp().getInt(getAppName() + "WIDTH", 200);
        int x = 0;//DEF.githubApp().getInt(getAppName() + "XPOS", 0);
        int y = 0;//DEF.githubApp().getInt(getAppName() + "YPOS", 0);
        if (h < dp2px(window.getContext(), 200)) {
            h = dp2px(window.getContext(), 200);
        }
        if (w < dp2px(window.getContext(), 200)) {
            w = dp2px(window.getContext(), 200);
        }
        return new StandOutLayoutParams(id, w, h, x, y, dp2px(window.getContext(), 56), dp2px(window.getContext(), 56));
    }

    public int getFlags(int id) {
        return (((StandOutFlags.FLAG_DECORATION_SYSTEM | StandOutFlags.FLAG_BODY_MOVE_ENABLE) | StandOutFlags.FLAG_WINDOW_HIDE_ENABLE) | StandOutFlags.FLAG_WINDOW_BRING_TO_FRONT_ON_TAP) | StandOutFlags.FLAG_WINDOW_EDGE_LIMITS_ENABLE;
    }

    public List<DropDownListItem> getDropDownItems(int id) {
        DropDownListItem item = new DropDownListItem(R.drawable.ic_home_white_24dp, "上传", new Runnable() {
            @Override
            public void run() {
                if (githubBedView != null) {
                    githubBedView.showHome();
                }
            }
        });
        DropDownListItem item1 = new DropDownListItem(R.drawable.ic_settings_white_24dp, "设置", new Runnable() {
            @Override
            public void run() {
                if (githubBedView != null) {
                    githubBedView.showSetting();
                }
            }
        });
        List<DropDownListItem> listItems = new ArrayList<>();
        listItems.add(item);
        listItems.add(item1);
        return listItems;
    }

    public void createAndAttachView(int id, FrameLayout frame) {
        this.publicId = id;
        githubBedView = new GithubBedView(getApplicationContext(), frame);
    }

    GithubBedView githubBedView;
}
