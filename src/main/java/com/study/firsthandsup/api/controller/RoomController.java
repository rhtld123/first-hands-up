package com.study.firsthandsup.api.controller;

import com.study.firsthandsup.api.service.RoomService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {

  private final RoomService roomService;

  @Schema(description = "방 생성")
  @PostMapping
  public ResponseEntity<String> create() {
    return ResponseEntity.ok(roomService.create(""));
  }

  @Schema(description = "방 참가")
  @PostMapping("/join/{roomId}")
  public ResponseEntity<Void> join(@PathVariable String roomId) {
    return ResponseEntity.ok().build();
  }

}
