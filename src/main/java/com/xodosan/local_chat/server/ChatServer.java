package com.xodosan.local_chat.server;

import com.xodosan.local_chat.constant.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
  public void start() {
    try (ServerSocket serverSocket = new ServerSocket(Constant.PORT)) {
      System.out.println("Chat Server is listening on port " + Constant.PORT);
      Socket socket = serverSocket.accept();

      try (InputStream in = socket.getInputStream();
           OutputStream out = socket.getOutputStream()) {
        byte[] buf = new byte[32 * 1024];
        int readBytes = in.read(buf);

        String line = new String(buf, 0 , readBytes);
        System.out.println(line);
      }
    } catch (IOException ex) {
      System.out.println("Error in the server: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ChatServer chatServer = new ChatServer();
    chatServer.start();
  }
}
