package com.codebay.vam.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * 自定义Toast以cancel之前未弹出的toast而弹出新的toast，不必等待旧的提示运行完才弹出当前的toast
 */

public class AppToast extends Toast {

    private static Toast toast = null;  // Global Toast
    private static WeakReference<Application> app;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public AppToast(Context context) {
        super(context);
    }

    public static void init(Application application) {
        app = new WeakReference<Application>(application);
    }

    /**
     * Display Toast
     *
     * @param resId The resource id of the string resource to use.  Can be formatted text.
     */
    public static void showToast(@StringRes int resId) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(app.get(), resId, LENGTH_SHORT);
        toast.show();
    }

    public static void showToast(String res) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(app.get(), res, LENGTH_SHORT);
        toast.show();
    }

    /**
     * Get a Toast object <br>
     * Need to call show() method to be displayed
     *
     * @return Toast object.
     */
    public static Toast getToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(app.get(), "", Toast.LENGTH_SHORT);
        return toast;
    }


}
