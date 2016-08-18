package com.github.tomakehurst.wiremock.jetty6;

import java.net.Socket;

public class ActiveSocket {

  private static final ThreadLocal<Socket> threadLocalSocket = new ThreadLocal<Socket>();

  public static Socket get() {
    return threadLocalSocket.get();
  }

  public static void clear() {
    threadLocalSocket.remove();
  }

  public static void set(final Socket socket) {
    threadLocalSocket.set(socket);
  }
}
