package com.github.tomakehurst.wiremock.jetty6;

import static com.github.tomakehurst.wiremock.common.Exceptions.throwUnchecked;

import java.io.IOException;
import java.net.Socket;

import javax.servlet.http.HttpServletResponse;

import com.github.tomakehurst.wiremock.core.FaultInjector;

public class Jetty6FaultInjector implements FaultInjector {

  private final HttpServletResponse response;

  public Jetty6FaultInjector(final HttpServletResponse response) {
    this.response = response;
  }

  public void emptyResponseAndCloseConnection() {
    try {
      ActiveSocket.get().close();
    } catch (IOException e) {
      throwUnchecked(e);
    }
  }

  public void malformedResponseChunk() {
    Socket socket = ActiveSocket.get();
    try {
      response.setStatus(200);
      response.flushBuffer();
      socket.getOutputStream().write("lskdu018973t09sylgasjkfg1][]'./.sdlv".getBytes());
      socket.close();
    } catch (IOException e) {
      throwUnchecked(e);
    }
  }

  public void randomDataAndCloseConnection() {
    Socket socket = ActiveSocket.get();
    try {
      socket.getOutputStream().write("lskdu018973t09sylgasjkfg1][]'./.sdlv".getBytes());
      socket.close();
    } catch (IOException e) {
      throwUnchecked(e);
    }
  }

  public void returnFault(final String responseContent) {
    Socket socket = ActiveSocket.get();
    try {
      socket.getOutputStream().write(responseContent.getBytes());
      socket.close();
    } catch (IOException e) {
      throwUnchecked(e);
    }

  }
}
