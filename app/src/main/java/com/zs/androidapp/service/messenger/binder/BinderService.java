package com.zs.androidapp.service.messenger.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

class BinderService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    class LocalBinder extends Binder{
        BinderService getService(){
            return BinderService.this;
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
