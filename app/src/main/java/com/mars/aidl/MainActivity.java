package com.mars.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * 多进程app可以在系统中申请多份内存，但应合理使用，建议把一些高消耗但不常用的模块放到独立的进程，不使用的进程可及时手动关闭；
 * 实现多进程的方式有多种：四大组件传递Bundle、Messenger、AIDL等，选择适合自己的使用场景；
 * Android中实现多进程通讯，建议使用系统提供的Binder类，该类已经实现了多进程通讯而不需要我们做底层工作；
 * 多进程应用，Application将会被创建多次；
 */
public class MainActivity extends AppCompatActivity {

    private MessageSender messageSender;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(Util.TAG, "onServiceConnected");
            messageSender = MessageSender.Stub.asInterface(iBinder);
            MessageModel messageModel = new MessageModel();
            messageModel.setFrom("client");
            messageModel.setTo("receiver");
            messageModel.setContent("this is message!");
            try {
                messageSender.registerReceiveListener(messageReceiver);
                messageSender.sendMessage(messageModel);
                //linkToDeath -> 设置死亡代理 DeathRecipient 对象；
                messageSender.asBinder().linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.d(Util.TAG, e.getMessage());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(Util.TAG, "onServiceDisconnected");
        }
    };

    /**
     * 增加了messageReceiver对象，用于监听服务端的消息通知；
     * onServiceConnected方法中，把messageReceiver注册到Service中去；
     * onDestroy时候解除messageReceiver的注册。
     */
    private MessageReceiver messageReceiver = new MessageReceiver.Stub() {
        @Override
        public void onMessageReceived(MessageModel receivedMessage) throws RemoteException {
            Log.d(Util.TAG, receivedMessage.toString());
        }
    };

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d(Util.TAG, "binderDied");
            if (messageSender != null) {
                //unlinkToDeath -> Binder死亡的情况下，解除该代理。
                messageSender.asBinder().unlinkToDeath(this, 0);
                messageSender = null;
            }
            setupService();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupService();
    }

    private void setupService() {
        Intent intent = new Intent(this, MessageService.class);
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        // isBinderAlive判断Binder是否死亡
        if (messageSender != null && messageSender.asBinder().isBinderAlive()) {
            try {
                messageSender.unregisterReceiveListener(messageReceiver);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.d(Util.TAG, e.getMessage());
            }
        }
        unbindService(serviceConnection);
        super.onDestroy();
    }
}