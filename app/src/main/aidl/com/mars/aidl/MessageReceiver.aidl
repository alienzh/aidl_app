// MessageReceiver.aidl
package com.mars.aidl;

import com.mars.aidl.MessageModel;

// Declare any non-default types here with import statements

interface MessageReceiver {

   void onMessageReceived(in MessageModel receivedMessage);
}