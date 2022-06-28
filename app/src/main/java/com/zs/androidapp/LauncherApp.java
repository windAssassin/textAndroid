package com.zs.androidapp;

import android.app.Application;
import android.content.res.Configuration;
import android.support.annotation.NonNull;

class LauncherApp extends Application {
   @Override
   //Application实例创建时调用
   public void onCreate() {
      super.onCreate();
   }

   @Override
   //监听应用配置信息发生改变的时候回调，如屏幕方向发生改变等
   public void onConfigurationChanged(@NonNull Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
   }


   @Override
   public void onLowMemory() {
      super.onLowMemory();
   }

   @Override
   //通知应用当前系统内存使用情况
   public void onTrimMemory(int level) {
      super.onTrimMemory(level);
   }



   @Override
   //注册对应用程序内所有activity生命周期的监听
   public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
      super.registerActivityLifecycleCallbacks(callback);
   }

   @Override
   public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
      super.unregisterActivityLifecycleCallbacks(callback);
   }
}
