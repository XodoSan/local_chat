package com.xodosan.local_chat.server;

import java.io.*;
import java.net.Socket;

public class MonoThreadClientHandler implements Runnable {
  private Socket client;

  public MonoThreadClientHandler(Socket client) {
    this.client = client;
  }

  @Override
  public void run() {
      try (DataInputStream in = new DataInputStream(client.getInputStream());
           DataOutputStream out = new DataOutputStream(client.getOutputStream())) {

      while (!client.isClosed()) {
        String entry = in.readUTF();

        if (entry.split(" ")[1].equals("quit")) {
          client.close();
          System.out.println(entry.split(" ")[0] + "disconnected");
          break;
        }

        out.writeUTF(entry);
        out.flush();
      }
    } catch (IOException e) {

    }
  }
}
