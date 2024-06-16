package com.study.firsthandsup.api.controller;

import com.study.firsthandsup.api.controller.request.HandsUpStartRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HandsUpController {

  private final SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/hands-up-start")
  public void start(HandsUpStartRequest request) {
    log.info("hands-up-start request: {}", request);
    simpMessagingTemplate.convertAndSend("/subscriber/rooms/%s".formatted(request.getRoomId()), "start");
  }
}
