/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.mars.aidl;
// Declare any non-default types here with import statements

public interface MessageSender extends android.os.IInterface
{
  /** Default implementation for MessageSender. */
  public static class Default implements com.mars.aidl.MessageSender
  {
    @Override public void sendMessage(com.mars.aidl.MessageModel messageModel) throws android.os.RemoteException
    {
    }
    @Override public void registerReceiveListener(com.mars.aidl.MessageReceiver messageReceiver) throws android.os.RemoteException
    {
    }
    @Override public void unregisterReceiveListener(com.mars.aidl.MessageReceiver messageReceiver) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.mars.aidl.MessageSender
  {
    private static final java.lang.String DESCRIPTOR = "com.mars.aidl.MessageSender";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.mars.aidl.MessageSender interface,
     * generating a proxy if needed.
     * 相同进程则返回 Stub；不同进程则返回 Stub.Proxy
     */
    public static com.mars.aidl.MessageSender asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.mars.aidl.MessageSender))) {
        return ((com.mars.aidl.MessageSender)iin);
      }
      return new com.mars.aidl.MessageSender.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }

    /**
     * 同一进程不会触发
     * 不同进程 asInterface 会返回 Stub.Proxy
     * 客户端调用 MessageSender.sendMessage 方法，触发跨进程数据传递
     * 最终 bindler 底层将处理好的数据回调给此方法，并调用我们真正的 sendMessage 方法
     */
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_sendMessage:
        {
          data.enforceInterface(descriptor);
          com.mars.aidl.MessageModel _arg0;
          if ((0!=data.readInt())) {
            _arg0 = com.mars.aidl.MessageModel.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.sendMessage(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_registerReceiveListener:
        {
          data.enforceInterface(descriptor);
          com.mars.aidl.MessageReceiver _arg0;
          _arg0 = com.mars.aidl.MessageReceiver.Stub.asInterface(data.readStrongBinder());
          this.registerReceiveListener(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_unregisterReceiveListener:
        {
          data.enforceInterface(descriptor);
          com.mars.aidl.MessageReceiver _arg0;
          _arg0 = com.mars.aidl.MessageReceiver.Stub.asInterface(data.readStrongBinder());
          this.unregisterReceiveListener(_arg0);
          reply.writeNoException();
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements com.mars.aidl.MessageSender
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }

      /**
       * Proxy 的 sendMessage 并不是直接调用我们定义的 sendMessage 方法，而是经过 Parcel 读写后，
       * 调用 mRemote.transact 方法，把数据交给 Bindler 处理，transact 处理完后，会调用上方的 onTransact
       * 方法，onTransact 拿到最终得到的参数数据，调用由我们真正的 sendMessage 方法。
       */
      @Override public void sendMessage(com.mars.aidl.MessageModel messageModel) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((messageModel!=null)) {
            _data.writeInt(1);
            messageModel.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          //调用 Binder 的 transact 方法进行多进程数据传输，处理完毕后调用上方的 onTransact
          boolean _status = mRemote.transact(Stub.TRANSACTION_sendMessage, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().sendMessage(messageModel);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void registerReceiveListener(com.mars.aidl.MessageReceiver messageReceiver) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongBinder((((messageReceiver!=null))?(messageReceiver.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_registerReceiveListener, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().registerReceiveListener(messageReceiver);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void unregisterReceiveListener(com.mars.aidl.MessageReceiver messageReceiver) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongBinder((((messageReceiver!=null))?(messageReceiver.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_unregisterReceiveListener, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().unregisterReceiveListener(messageReceiver);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      public static com.mars.aidl.MessageSender sDefaultImpl;
    }
    static final int TRANSACTION_sendMessage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_registerReceiveListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_unregisterReceiveListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    public static boolean setDefaultImpl(com.mars.aidl.MessageSender impl) {
      // Only one user of this interface can use this function
      // at a time. This is a heuristic to detect if two different
      // users in the same process use this function.
      if (Stub.Proxy.sDefaultImpl != null) {
        throw new IllegalStateException("setDefaultImpl() called twice");
      }
      if (impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static com.mars.aidl.MessageSender getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  public void sendMessage(com.mars.aidl.MessageModel messageModel) throws android.os.RemoteException;
  public void registerReceiveListener(com.mars.aidl.MessageReceiver messageReceiver) throws android.os.RemoteException;
  public void unregisterReceiveListener(com.mars.aidl.MessageReceiver messageReceiver) throws android.os.RemoteException;
}
