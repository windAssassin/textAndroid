package com.zs.androidapp.service.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class MessengerActivity extends ComponentActivity {


    private Messenger mMessenger;
    private boolean mbound;
    class ReplyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case MessengerService.MSG_REPLY:
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }


    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取到服务端的信使messenger用于给服务端发送消息
            mMessenger = new Messenger(service);

            Message message = Message.obtain(null, MessengerService.MSG_SAY_HELLO);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "你好！我是客户端。");
            message.setData(bundle);
            //设置replyTo为客户端的信使messenger,以便于服务端发送消息给客户端
            message.replyTo = new Messenger(new ReplyHandler());
            try {
                mMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mbound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mbound = false;
        }
    };

    @Override
    protected void onCreate(@Nullable @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindService(new Intent(this, MessengerService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
        mbound = false;
    }
}
