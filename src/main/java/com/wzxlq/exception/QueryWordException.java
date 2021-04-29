package com.wzxlq.exception;

import lombok.Data;

/**
 * @author 李倩
 */
@Data
public class QueryWordException extends RuntimeException {
  private Integer code;

  public QueryWordException(String message, Integer code) {
    super(message);
    this.code = code;
  }
}
