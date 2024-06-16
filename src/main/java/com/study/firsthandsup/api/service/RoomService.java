package com.study.firsthandsup.api.service;

import com.study.firsthandsup.api.service.exception.RoomNotFoundException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

  private static final String ROOM_PREFIX = "room:%s";
  private final RedisTemplate<String, String> redisTemplate;

  public String create(String token) {
    String roomId = UUID.randomUUID().toString();
    String roomKey = ROOM_PREFIX.formatted(roomId);
    redisTemplate.opsForSet().add(roomKey, token);
    return roomId;
  }

  public void join(String token, String roomId) {
    String roomKey = ROOM_PREFIX.formatted(roomId);
    if (!hasRoom(roomKey)) {
      throw new RoomNotFoundException("방이 존재하지 않습니다. roomId = " + roomId);
    }
    redisTemplate.opsForSet().add(roomKey, token);
  }

  private boolean hasRoom(String roomKey) {
    Long joinMemberCount = Optional.ofNullable(redisTemplate.opsForSet().size(roomKey)).orElse(0L);
    return joinMemberCount > 0;
  }
}
