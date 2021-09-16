// MessageSender.aidl
package com.mars.aidl;

import com.mars.aidl.MessageModel;
import com.mars.aidl.MessageReceiver;

// Declare any non-default types here with import statements

interface MessageSender {

   void sendMessage(in MessageModel messageModel);

   void registerReceiveListener(MessageReceiver messageReceiver);

   void unregisterReceiveListener(MessageReceiver messageReceiver);
}