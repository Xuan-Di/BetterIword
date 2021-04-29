package com.wzxlq.exception;

import lombok.Data;

/**
 * @author 李倩
 */
@Data
public class OpenIdNULLException extends RuntimeException{
    private Integer code;

  public OpenIdNULLException(String message, Integer code) {
    super(message);
    this.code = code;
  }
}
