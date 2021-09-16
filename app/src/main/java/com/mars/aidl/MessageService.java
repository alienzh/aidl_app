package com.mars.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

public class MessageService extends Service {

    private AtomicBoolean serviceStop = new AtomicBoolean(false);

    /**
     * registerReceiveListener 和 unregisterReceiveListener在客户端传输过来的对象，
     * 经过Binder处理，在服务端接收到的时候其实是一个新的对象，这样导致在 unregisterReceiveListener
     * 的时候，普通的ArrayList是无法找到在 registerReceiveListener 时候添加到List的那个对象的，
     * 但是它们底层使用的Binder对象是同一个，RemoteCallbackList利用这个特性做到了可以找到同一个对象，
     * 这样我们就可以顺利反注册客户端传递过来的接口对象了。
     */
    RemoteCallbackList<MessageReceiver> receiverRemoteCallbackList = new RemoteCallbackList<>();

    IBinder messageBinder = new MessageSender.Stub() {
        @Override
        public void sendMessage(MessageModel messageModel) throws RemoteException {
            Log.d(Util.TAG, messageModel.toString());
        }

        @Override
        public void registerReceiveListener(MessageReceiver messageReceiver) throws RemoteException {
            receiverRemoteCallbackList.register(messageReceiver);
        }

        @Override
        public void unregisterReceiveListener(MessageReceiver messageReceiver) throws RemoteException {
            receiverRemoteCallbackList.unregister(messageReceiver);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            /**
             * 包名验证方式
             */
            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            if (packageName == null || !packageName.startsWith("com.mars.aidl")) {
                Log.d(Util.TAG, "onTransact拒绝调用：" + packageName);
                return false;
            }
            return super.onTransact(code, data, reply, flags);
        }
    };

    public MessageService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        //自定义permission方式检查权限
        if (checkCallingOrSelfPermission("com.mars.aidl.permission.REMOTE_SERVICE_PERMISSION")
                == PackageManager.PERMISSION_DENIED) {
            return null;
        }
        return messageBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // MessageSender.Stub实现了注册和反注册回调接口的方法；
        //增加了RemoteCallbackList来管理AIDL远程接口；
        //FakeTCPTask模拟了长连接通知客户端有新消息到达。
        Util.exeSingleTask(new FakeTCPTask());
    }

    @Override
    public void onDestroy() {
        serviceStop.set(true);
        super.onDestroy();
    }

    /**
     * 模拟长连接，通知客户端有新消息到达
     */
    private class FakeTCPTask implements Runnable {

        @Override
        public void run() {
            while (!serviceStop.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MessageModel messageModel = new MessageModel();
                messageModel.setFrom("Service");
                messageModel.setTo("Client");
                messageModel.setContent(String.valueOf(System.currentTimeMillis()));

                /**
                 * RemoteCallbackList的遍历方式
                 * beginBroadcast和finishBroadcast一定要配对使用
                 */
                final int listenerCount = receiverRemoteCallbackList.beginBroadcast();
                Log.d(Util.TAG, "listenerCount == " + listenerCount);
                for (int i = 0; i < listenerCount; i++) {
                    MessageReceiver messageReceiver = receiverRemoteCallbackList.getBroadcastItem(i);
                    if (messageReceiver != null) {
                        try {
                            messageReceiver.onMessageReceived(messageModel);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
                receiverRemoteCallbackList.finishBroadcast();
            }
        }
    }
}