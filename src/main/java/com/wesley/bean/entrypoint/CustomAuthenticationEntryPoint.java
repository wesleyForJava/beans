package com.wesley.bean.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**
 * 自定义401错误码内容
 * @author pc
 *
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{
	 private final Logger log = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		   log.info("Pre-authenticated entry point called. Rejecting access");
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
	}

}
