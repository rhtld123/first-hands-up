package com.study.firsthandsup.common;

import lombok.Getter;

@Getter
public class ResponseDto<T> {

  private T data;
  private Error error;

  public ResponseDto(T data, Error error) {
    this.error = error;
    this.data = data;
  }

  @Getter
  public static class Error {

    private String message;
    private String path;
  }

  public static <T> ResponseDto<T> ok(T data) {
    return new ResponseDto<>(data, null);
  }

  public static <T> ResponseDto<T> ok() {
    return new ResponseDto<>(null, null);
  }
}
