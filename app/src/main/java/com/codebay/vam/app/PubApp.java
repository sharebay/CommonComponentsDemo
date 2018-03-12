package com.codebay.vam.app;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.codebay.vam.utils.AppToast;
import com.codebay.vam.utils.LogUtils;
import com.codebay.vam.utils.crash_log_catch.CrashHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by GY on 2018/3/10.
 *
 * PubApp 可以用来初始化保存有数据的单例，例如当前登录信息（时间，用户名，角色信息等）？X
 *  初始化 SharedPreferences？
 *  初始化数据库？
 *  注册广播？
 *  记录打开的Activity，然后突出程序的时候关闭所有？
 *  初始化线程池？
 *  启动 服务？
 *  监听程序 运行的状态
 */

public class PubApp extends Application {
    private static final String TAG = "PubApp";
    private static Context mContext;
    private List<Activity> activityList = new LinkedList<Activity>();
    private ThreadPoolExecutor executor;

    //用于记录App的使用信息
    private boolean isBackground = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        CrashHandler.getInstance().init(this);
        LogUtils.setTag(TAG);
        initPool();
        initLib();

        listenForForeground();
        listenForScreenTurningOff();
    }

    private void initPool() {
        executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
    }

    public static Context getInstance() {
        return mContext;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    @Override
    public void onTerminate(){
        super.onTerminate();
        finishThreads();
        LogUtils.d("123","11111");
        AppToast.showToast("应用退出");
    }

    public void finishThreads(){
        if (executor != null){
            executor.shutdown();
        }
    }

    /*初始化框架*/
    public void initLib(){
        //图片异步加载框架 ImageLoaderConfiguration
    }


    /*添加Activity的生命周期方法回调*/
    private void listenForForeground() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (isBackground) {
                    isBackground = false;
                    notifyForeground();
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    /*监听息屏广播*/
    private void listenForScreenTurningOff() {
        final IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                isBackground = true;
                if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                    notifyScreenOff();
                } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
                    notifyScreenOn();
                }
            }
        }, screenStateFilter);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            isBackground = true;
            notifyBackground();
        }

    }

    private void notifyForeground() {
        // This is where you can notify listeners, handle session tracking, etc
        AppToast.showToast("进入前台");
        LogUtils.d("123","111112222 进入前台");
    }

    private void notifyBackground() {
        // This is where you can notify listeners, handle session tracking, etc
        AppToast.showToast("进入后台");
        LogUtils.d("qqq");
        LogUtils.d("qqq","qqq 进入后台");
    }

    private void notifyScreenOff() {
        // This is where you can notify listeners, handle session tracking, etc
        AppToast.showToast("灭屏");
    }

    private void notifyScreenOn() {
        // This is where you can notify listeners, handle session tracking, etc
        AppToast.showToast("亮屏");
    }

    public boolean isBackground() {
        return isBackground;
    }

}
