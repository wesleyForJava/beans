package com.wesley.bean.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getRequestURI());
		//登录不做拦截
		if(request.getRequestURI().equals("/user1/login_view")|| request.getRequestURI().equals("/logger/record") || request.getRequestURI().equals("/userentity/login")) {
			return true;
		}
		//验证Session是否存在
		Object session = request.getSession().getAttribute("_session_user");
		if(session==null) {
			response.sendRedirect("/user1/login_view");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
