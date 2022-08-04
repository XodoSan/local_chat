package com.xodosan.local_chat;

import com.xodosan.local_chat.constant.Constant;
import com.xodosan.local_chat.server.ChatServer;

public class Main {

  public static void main(String[] args) {
    ChatServer chatServer = new ChatServer(Constant.PORT);
    chatServer.start();
  }
}
