package com.xodosan.local_chat.server;

import com.xodosan.local_chat.constant.Constant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
  int port = Constant.PORT;

  public void run() {
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Server listening on port: " + port);

      while (!serverSocket.isClosed()) {
        Socket client = serverSocket.accept();
        new Thread(new MonoThreadClientHandler(client)).start();
      }
    } catch (IOException ex) {
      System.out.println("Error in the server: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ChatServer chatServer = new ChatServer();
    chatServer.run();
  }
}
