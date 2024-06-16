package com.study.firsthandsup.api.service.exception;

public class RoomNotFoundException extends RuntimeException {

  public RoomNotFoundException(String message) {
    super(message);
  }
}
