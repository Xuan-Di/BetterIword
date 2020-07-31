package com.wzxlq.exception;

import lombok.Data;

/**
 * @author 王照轩
 * @date 2020/4/26 - 15:10
 */
@Data
public class QueryWordException extends RuntimeException {
  private Integer code;

  public QueryWordException(String message, Integer code) {
    super(message);
    this.code = code;
  }
}
