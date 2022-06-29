package com.zs.androidapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * onStartCommand的三个返回值
 * 1、START_NOT_STICKY
 *      如果系统在onStartCommand()返回后终止服务，则除非有待传递的挂起Intent，否则系统不会重建服务
 * 2、START_STICKY
 *      如果系统在onStartCommand()返回后终止服务，则其会重建服务并调用onStartCommand()，
 *      但不会重新传递最后一个Intent。相反，除非有挂起Intent要启动服务，否则系统会调用包含空
 *      Intent 的 onStartCommand()。在此情况下，系统会传递这些 Intent。
 *      此常量适用于不执行命令、但无限期运行并等待作业的媒体播放器。
 * 3、START_REDELIVER_INTENT
 *      如果系统在 onStartCommand() 返回后终止服务，则其会重建服务，
 *      并通过传递给服务的最后一个 Intent 调用 onStartCommand()。所有挂起 Intent 均依次传递。
 *      此常量适用于主动执行应立即恢复的作业（例如下载文件）的服务
 *
 *
 * service生命周期解析：
 *      如果服务尚未运行，则会首先调用onCreate(),然后在调用onStartCommand()。
 *      如果是多次调用同一服务的startService()，则会多次调用onStartCommand(),但是调用一次stopService或者stopSelf。
 *
 *
 *
 *
 * 前台服务：
 *      前台服务不会在内存不足的时候主动销毁，前台服务必须为状态栏提供通知，这就意味着除非从前台移除或者销毁服务，
 *      否则不能清除该通知。
 *      前台服务必须显示优先级为PRIORITY_LOW或更高的状态栏通知，这有助于确保用户知道应用正在执行的任务。
 *      如果某操作不是特别重要，则不适合使用前台服务通知。
 *      如果应用面向 Android 9（API 级别 28）或更高版本并使用前台服务，则其必须请求FOREGROUND_SERVICE 权限。
 *      如果应用试图创建前台服务但未请求FOREGROUND_SERVICE，则系统会抛出SecurityException
 */
public class LauncherService extends Service {
    public LauncherService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }




    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /**
         * 使用Notification创建一个前台服务通知：
         *      使用startForeground()启动一个前台服务，接收两个参数：唯一标识通知的整型(不能为0)和Notification。
         *      使用stopForeground()将服务从前台移出但是此方法不会停止服务，接收一个boolean参数标识是否移除通知。
         *      当停止前台服务时状态栏也会被移除。
         */
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification =
                new Notification.Builder(this, "")
                        .setContentTitle("getText(R.string.notification_title)")
                        .setContentText("getText(R.string.notification_message)")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentIntent(pendingIntent)
                        .setTicker("getText(R.string.ticker_text)")
                        .build();

        startForeground(1, notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}