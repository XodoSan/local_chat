package com.xodosan.local_chat.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MonoThreadClientHandler implements Runnable {
  private Socket client;

  public MonoThreadClientHandler(Socket client) {
    this.client = client;
  }

  @Override
  public void run() {
    try (InputStream in = client.getInputStream();
         OutputStream out = client.getOutputStream()) {


      System.out.println("Server reading from channel");
      String entry = new String(in.readAllBytes());

      System.out.println("READ from client message - " + entry);

      out.flush();


      System.out.println("Client disconnected");
      System.out.println("Closing connections & channels.");

      client.close();

      System.out.println("Closing connections & channels - DONE.");
    } catch (IOException e) {

    }
  }
}
