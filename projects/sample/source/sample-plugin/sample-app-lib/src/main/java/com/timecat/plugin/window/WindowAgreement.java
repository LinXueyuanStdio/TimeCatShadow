package com.timecat.plugin.window;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/14
 * @description null
 * @usage null
 */
public class WindowAgreement {

    /**
     * Intent action: Show a new window corresponding to the id.
     */
    public static final String ACTION_SHOW = "SHOW";

    /**
     * Intent action: Restore a previously hidden window corresponding to the id. The window should be
     * previously hidden with {@link #ACTION_HIDE}.
     */
    public static final String ACTION_RESTORE = "RESTORE";

    /**
     * Intent action: Close an existing window with an existing id.
     */
    public static final String ACTION_CLOSE = "CLOSE";

    /**
     * Intent action: Close all existing windows.
     */
    public static final String ACTION_CLOSE_ALL = "CLOSE_ALL";

    /**
     * Intent action: Send data to a new or existing window.
     */
    public static final String ACTION_SEND_DATA = "SEND_DATA";

    /**
     * Intent action: Hide an existing window with an existing id. To enable the ability to restore
     * this window, make sure you implement {@link StandOutWindow#getHiddenNotification(int)}.
     */
    public static final String ACTION_HIDE = "HIDE";

    public static void safeStartService(Context context, Intent intent) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.startForegroundService(intent);
//        } else {
//        }
            context.startService(intent);
    }


    /**
     * Show a new window corresponding to the id, or restore a previously hidden window.
     *
     * @param context A Context of the application package implementing this class.
     * @param cls     The Service extending {@link StandOutWindow} that will be used to create and manage
     *                the window.
     * @param id      The id representing this window. If the id exists, and the corresponding window was
     *                previously hidden, then that window will be restored.
     * @see StandOutWindow#show(int)
     */
    public static void show(Context context,
                            Class<? extends StandOutWindow> cls, int id) {
        safeStartService(context, getShowIntent(context, cls, id));
    }

    /**
     * Hide the existing window corresponding to the id. To enable the ability to restore this window,
     * make sure you implement {@link StandOutWindow#getHiddenNotification(int)}.
     *
     * @param context A Context of the application package implementing this class.
     * @param cls     The Service extending {@link StandOutWindow} that is managing the window.
     * @param id      The id representing this window. The window must previously be shown.
     * @see StandOutWindow#hide(int)
     */
    public static void hide(Context context,
                            Class<? extends StandOutWindow> cls, int id) {
        safeStartService(context, getHideIntent(context, cls, id));
    }

    /**
     * Close an existing window with an existing id.
     *
     * @param context A Context of the application package implementing this class.
     * @param cls     The Service extending {@link StandOutWindow} that is managing the window.
     * @param id      The id representing this window. The window must previously be shown.
     * @see StandOutWindow#close(int)
     */
    public static void close(Context context,
                             Class<? extends StandOutWindow> cls, int id) {
        safeStartService(context, getCloseIntent(context, cls, id));
    }

    /**
     * Close all existing windows.
     *
     * @param context A Context of the application package implementing this class.
     * @param cls     The Service extending {@link StandOutWindow} that is managing the window.
     * @see StandOutWindow#closeAll()
     */
    public static void closeAll(Context context,
                                Class<? extends StandOutWindow> cls) {
        safeStartService(context, getCloseAllIntent(context, cls));
    }


    /**
     * This allows windows of different applications to communicate with each other.
     * <p>
     * <p>
     * Send {@link android.os.Parcelable} data in a {@link Bundle} to a new or existing windows. The
     * implementation of the recipient window can handle what to do with the data. To receive a
     * result, provide the class and id of the sender.
     *
     * @param context     A Context of the application package implementing the class of the sending
     *                    window.
     * @param toCls       The Service's class extending {@link StandOutWindow} that is managing the
     *                    receiving window.
     * @param toId        The id of the receiving window, or DISREGARD_ID.
     * @param requestCode Provide a request code to declare what kind of data is being sent.
     * @param data        A bundle of Parcelable data to be sent to the receiving window.
     * @param fromCls     Provide the class of the sending window if you want a result.
     * @param fromId      Provide the id of the sending window if you want a result.
     * @see StandOutWindow#sendData(int, Class, int, int, Bundle)
     */
    public static void sendData(Context context,
                                Class<? extends StandOutWindow> toCls, int toId, int requestCode,
                                Bundle data, Class<? extends StandOutWindow> fromCls, int fromId) {
        safeStartService(context, getSendDataIntent(context, toCls, toId,
                requestCode, data, fromCls, fromId));
    }


    /**
     * See {@link #show(Context, Class, int)}.
     *
     * @param context A Context of the application package implementing this class.
     * @param cls     The Service extending {@link StandOutWindow} that will be used to create and manage
     *                the window.
     * @param id      The id representing this window. If the id exists, and the corresponding window was
     *                previously hidden, then that window will be restored.
     * @return An {@link Intent} to use with {@link Context#startService(Intent)}.
     */
    public static Intent getShowIntent(Context context,
                                       Class<? extends StandOutWindow> cls, int id) {
        boolean cached = StandOutWindow.sWindowCache.isCached(id, cls);
        String action = cached ? ACTION_RESTORE : ACTION_SHOW;
        Uri uri = cached ? Uri.parse("standout://" + cls + '/' + id) : null;
        return new Intent(context, cls)
                .putExtra("id", id)
                .setAction(action)
                .setData(uri);
    }

    /**
     * See {@link #hide(Context, Class, int)}.
     *
     * @param context A Context of the application package implementing this class.
     * @param cls     The Service extending {@link StandOutWindow} that is managing the window.
     * @param id      The id representing this window. If the id exists, and the corresponding window was
     *                previously hidden, then that window will be restored.
     * @return An {@link Intent} to use with {@link Context#startService(Intent)}.
     */
    public static Intent getHideIntent(Context context,
                                       Class<? extends StandOutWindow> cls, int id) {
        return new Intent(context, cls)
                .putExtra("id", id)
                .setAction(ACTION_HIDE);
    }

    /**
     * See {@link #close(Context, Class, int)}.
     *
     * @param context A Context of the application package implementing this class.
     * @param cls     The Service extending {@link StandOutWindow} that is managing the window.
     * @param id      The id representing this window. If the id exists, and the corresponding window was
     *                previously hidden, then that window will be restored.
     * @return An {@link Intent} to use with {@link Context#startService(Intent)}.
     */
    public static Intent getCloseIntent(Context context,
                                        Class<? extends StandOutWindow> cls, int id) {
        return new Intent(context, cls)
                .putExtra("id", id)
                .setAction(ACTION_CLOSE);
    }

    /**
     * See {@link #closeAll(Context, Class)}.
     *
     * @param context A Context of the application package implementing this class.
     * @param cls     The Service extending {@link StandOutWindow} that is managing the window.
     * @return An {@link Intent} to use with {@link Context#startService(Intent)}.
     */
    public static Intent getCloseAllIntent(Context context,
                                           Class<? extends StandOutWindow> cls) {
        return new Intent(context, cls)
                .setAction(ACTION_CLOSE_ALL);
    }

    /**
     * See {@link #sendData(Context, Class, int, int, Bundle, Class, int)}.
     *
     * @param context     A Context of the application package implementing the class of the sending
     *                    window.
     * @param toCls       The Service's class extending {@link StandOutWindow} that is managing the
     *                    receiving window.
     * @param toId        The id of the receiving window.
     * @param requestCode Provide a request code to declare what kind of data is being sent.
     * @param data        A bundle of Parcelable data to be sent to the receiving window.
     * @param fromCls     If the sending window wants a result, provide the class of the sending window.
     * @param fromId      If the sending window wants a result, provide the id of the sending window.
     * @return An {@link Intent} to use with {@link Context#startService(Intent)}.
     */
    public static Intent getSendDataIntent(Context context,
                                           Class<? extends StandOutWindow> toCls, int toId, int requestCode,
                                           Bundle data, Class<? extends StandOutWindow> fromCls, int fromId) {
        return new Intent(context, toCls).putExtra("id", toId)
                .putExtra("requestCode", requestCode)
                .putExtra("wei.mark.standout.data", data)
                .putExtra("wei.mark.standout.fromCls", fromCls)
                .putExtra("fromId", fromId)
                .setAction(ACTION_SEND_DATA);
    }

}
