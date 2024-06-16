package com.study.firsthandsup.api.controller.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HandsUpStartRequest {

  private String roomId;
  private String token;
}
