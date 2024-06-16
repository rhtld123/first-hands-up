package com.study.firsthandsup.api.controller;

import com.study.firsthandsup.api.service.RoomService;
import com.study.firsthandsup.common.ResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {

  private final RoomService roomService;

  @Schema(description = "방 생성")
  @PostMapping
  public ResponseDto<String> create(@RequestHeader("token") String token) {
    return ResponseDto.ok(roomService.create(token));
  }

  @Schema(description = "방 참가")
  @PostMapping("/join/{roomId}")
  public ResponseDto<Void> join(@PathVariable String roomId, @RequestHeader("token") String token) {
    roomService.join(token, roomId);
    return ResponseDto.ok();
  }
}
