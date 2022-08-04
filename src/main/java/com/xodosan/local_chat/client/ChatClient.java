package com.xodosan.local_chat.client;

import com.xodosan.local_chat.constant.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
  private Scanner scanner = new Scanner(System.in);

  public void start() {
    try (Socket socket = new Socket(Constant.HOST, Constant.PORT)) {
      String line = scanner.nextLine();

      try (InputStream in = socket.getInputStream();
           OutputStream out = socket.getOutputStream()) {
        out.write(line.getBytes());
        out.flush();
      }
    } catch (IOException ex) {
      System.out.println("Error in the server: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ChatClient chatClient = new ChatClient();
    chatClient.start();
  }
}
