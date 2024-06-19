package com.study.firsthandsup.api.controller;

import com.study.firsthandsup.api.controller.request.HandsUpStartRequest;
import com.study.firsthandsup.api.service.HandsUpService;
import com.study.firsthandsup.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HandsUpController {

  private final SimpMessagingTemplate simpMessagingTemplate;
  private final HandsUpService handsUpService;

  @MessageMapping("/hands-up-start")
  public void start(HandsUpStartRequest request) {
    log.info("hands-up-start request: {}", request);
    simpMessagingTemplate.convertAndSend("/subscriber/rooms/%s".formatted(request.getRoomId()), "start");
  }

//  @MessageMapping("/show-result")

  @PostMapping("/api/v1/rooms/{roomId}/hands-up")
  public ResponseDto<Void> handUp(@RequestHeader("token") String token, @PathVariable("roomId") String roomId) {
    handsUpService.handsUp(token, roomId);
    return ResponseDto.ok();
  }

  @DeleteMapping("/api/v1/rooms/{roomId}/hands-up")
  public ResponseDto<Void> clear(@RequestHeader("token") String token, @PathVariable("roomId") String roomId) {
    handsUpService.clear(token, roomId);
    return ResponseDto.ok();
  }
}
