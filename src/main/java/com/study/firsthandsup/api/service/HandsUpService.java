package com.study.firsthandsup.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HandsUpService {

  private final RedisTemplate<String, String> redisTemplate;
  private static final String HANDS_UP_PREFIX = "room:%s:handsup";

  @Transactional
  public void handsUp(String token, String roomId) {
    final long now = System.currentTimeMillis();
    redisTemplate.opsForZSet().add(HANDS_UP_PREFIX.formatted(roomId), token, now);
  }

  @Transactional
  public void clear(String token, String roomId) {
    redisTemplate.delete(HANDS_UP_PREFIX.formatted(roomId));
  }
}
