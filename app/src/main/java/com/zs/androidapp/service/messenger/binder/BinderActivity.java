package com.zs.androidapp.service.messenger.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

class BinderActivity extends ComponentActivity {
    private BinderService mBinderService;
    /**
     * 使用IBinder实现客户端与服务端的通信，此方法只适用于客户端和服务端在同一进程中
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BinderService.LocalBinder binder = (BinderService.LocalBinder) service;
            mBinderService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, BinderService.class);

        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
    }
}
