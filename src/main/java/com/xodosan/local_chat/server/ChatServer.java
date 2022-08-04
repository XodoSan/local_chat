package com.xodosan.local_chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatServer {
  private static int port;

  public ChatServer(int port) {
    this.port = port;
  }

  public void start() {
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      while (true) {
        Socket socket = serverSocket.accept();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter output = new PrintWriter(socket.getOutputStream())) {

          while (!input.ready()) ;

          System.out.println();
          while (input.ready()) {
            System.out.println(input.readLine());
          }
        }
      }
    } catch (IOException e) {
      // logger work
    }
  }
}
