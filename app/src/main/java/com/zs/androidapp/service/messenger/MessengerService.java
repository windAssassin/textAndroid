package com.zs.androidapp.service.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class MessengerService extends Service {

    final static int MSG_SAY_HELLO = 1;
    final static int MSG_REPLY = 2;

    //此handler运行在messenger的线程中
    class MessengerHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case MSG_SAY_HELLO:
                    Toast.makeText(MessengerService.this, msg.getData().getString("msg"),
                            Toast.LENGTH_SHORT).show();
                    //获取来自客户端的信使messenger
                    Messenger replyTo = msg.replyTo;
                    Message message = Message.obtain(null, MSG_REPLY);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "服务端已收到消息");
                    message.setData(bundle);
                    try {
                        //发送消息给客户端
                        replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        /**
         * 通过messenger实现客户端和服务端的通信，此种方式可以适用于进程间通信
         * 此种方法会把消息加入到messenger的消息队列中
         */
        Messenger messenger = new Messenger(new MessengerHandler());
        return messenger.getBinder();
    }
}
