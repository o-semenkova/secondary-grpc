package com.grpc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetMessagesEndpoint {
  AppendMessageServiceImpl getMessagesService;

  public GetMessagesEndpoint(AppendMessageServiceImpl getMessagesService) {
    this.getMessagesService = getMessagesService;
  }

  @GetMapping("/getall")
  public String getall() {
    return getMessagesService.getAllMessages();
  }
}
