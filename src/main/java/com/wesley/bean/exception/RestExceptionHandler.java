package com.wesley.bean.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wesley.bean.pojo.ApiResult;

@ControllerAdvice(annotations=RestController.class)
@ResponseBody
public class RestExceptionHandler {
  /**
   * 默认统一异常处理方法
   */
	@SuppressWarnings("static-access")
	@ExceptionHandler
	@ResponseStatus
	public ApiResult getRuntimeException(Exception e) {
		return new ApiResultGenerator().errorResult(e.getMessage(),e);
		
	}
	
}
