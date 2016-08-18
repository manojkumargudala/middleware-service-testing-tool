package com.github.tomakehurst.wiremock.admin;

import com.github.tomakehurst.wiremock.common.Json;
import com.github.tomakehurst.wiremock.core.Admin;
import com.github.tomakehurst.wiremock.global.RequestDelaySpec;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

public class SocketDelayTask implements AdminTask {

  public ResponseDefinition execute(final Admin admin, final Request request) {
    RequestDelaySpec delaySpec = Json.read(request.getBodyAsString(), RequestDelaySpec.class);
    admin.addSocketAcceptDelay(delaySpec);
    return ResponseDefinition.ok();
  }
}
