package com.zs.androidapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

/**
 * 发送广播的方式：
 * 1、sendOrderedBroadcast(Intent, String)发送顺序广播，广播接收器可以终止广播还可以改变广播的内容
 * 2、LocalBroadcastManager.sendBroadcast()发送本地广播，这种发送方式只有本应用中的接收器才可以收到
 * 3、sendBroadcast(intent, String)发送正常广播
 *
 *
 * 限制广播：
 * 1、通过permission限制广播，只有具有特定权限的接收器才可以收到广播：
 *       例如：Intent intent = new Intent();
 *            intent.setAction("android.intent.action.ACTION_POWER_CONNECTED")
 *            sendBroadcast(intent, Manifest.permission.SEND_SMS);
 * 2、设置setPackage()相应的包名
 * 3、使用LocalBroadcastManager发送本地广播
 *
 *
 *
 * 在注册接收器时尽量使用动态注册，如果必须在清单文件中注册接收器则尽量把接收器限定在应用内：
 * 1、设置permission
 * 2、exported设为false
 *
 *
 *
 * 在android 8.0或者更高版本中，无法在清单文件中注册一些接收系统广播的广播接收器
 */
class LauncherBroadcastReceiver extends BroadcastReceiver {
   public static final String TAG = "LauncherBroadcastReceiver";

   @Override
   public void onReceive(Context context, Intent intent) {
      //使用goAsync()在接收器中处理一些耗时操作，这种方式可以解决onReceive()执行结束时，
      // 接收器即被回收从而线程也别回收的问题
      PendingResult pendingResult = goAsync();
      Task task = new Task(pendingResult, intent);
      task.execute();
   }


   class Task extends AsyncTask<String, Integer, String>{

      private PendingResult mResult;
      private Intent mIntent;

      public Task(PendingResult result, Intent intent){
         mResult = result;
         mIntent = intent;
      }

      @Override
      protected String doInBackground(String... strings) {
         StringBuilder sb = new StringBuilder();

         return sb.toString();
      }

      @Override
      protected void onPostExecute(String s) {
         super.onPostExecute(s);
         //Must call finish() so the BroadcastReceiver can be recycled.
         mResult.finish();
      }
   }
}
