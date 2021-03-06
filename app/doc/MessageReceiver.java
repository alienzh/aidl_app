/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.mars.aidl;
// Declare any non-default types here with import statements

public interface MessageReceiver extends android.os.IInterface
{
  /** Default implementation for MessageReceiver. */
  public static class Default implements com.mars.aidl.MessageReceiver
  {
    @Override public void onMessageReceived(com.mars.aidl.MessageModel receivedMessage) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.mars.aidl.MessageReceiver
  {
    private static final java.lang.String DESCRIPTOR = "com.mars.aidl.MessageReceiver";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.mars.aidl.MessageReceiver interface,
     * generating a proxy if needed.
     */
    public static com.mars.aidl.MessageReceiver asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.mars.aidl.MessageReceiver))) {
        return ((com.mars.aidl.MessageReceiver)iin);
      }
      return new com.mars.aidl.MessageReceiver.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
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
        case TRANSACTION_onMessageReceived:
        {
          data.enforceInterface(descriptor);
          com.mars.aidl.MessageModel _arg0;
          if ((0!=data.readInt())) {
            _arg0 = com.mars.aidl.MessageModel.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.onMessageReceived(_arg0);
          reply.writeNoException();
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements com.mars.aidl.MessageReceiver
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
      @Override public void onMessageReceived(com.mars.aidl.MessageModel receivedMessage) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((receivedMessage!=null)) {
            _data.writeInt(1);
            receivedMessage.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_onMessageReceived, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().onMessageReceived(receivedMessage);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      public static com.mars.aidl.MessageReceiver sDefaultImpl;
    }
    static final int TRANSACTION_onMessageReceived = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    public static boolean setDefaultImpl(com.mars.aidl.MessageReceiver impl) {
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
    public static com.mars.aidl.MessageReceiver getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  public void onMessageReceived(com.mars.aidl.MessageModel receivedMessage) throws android.os.RemoteException;
}
