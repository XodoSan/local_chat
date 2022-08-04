package com.xodosan.local_chat.client;

import com.xodosan.local_chat.constant.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
  private Scanner scanner = new Scanner(System.in);
  private String thisHost = Constant.HOST;
  private int thisPort = Constant.PORT;

  public void run() {
    System.out.print("Enter your nickname: ");
    String nickname = scanner.nextLine();

    while (true) {
      try (Socket socket = new Socket(thisHost, thisPort)) {
        System.out.print("Enter your message: ");
        String line = scanner.nextLine();
        String resultLine = nickname + ':' + ' ' + line;

        try (InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream()) {
          out.write(resultLine.getBytes());
          out.flush();
        }

      } catch (IOException ex) {
        System.out.println("Error in the server: " + ex.getMessage());
        ex.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    ChatClient chatClient = new ChatClient();
    chatClient.run();
  }
}
