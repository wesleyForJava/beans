package com.wesley.bean.util;

import javax.servlet.http.HttpServletRequest;

public class LoggerUtils {
	 public static final String LOGGER_RETURN = "_logger_return";

	public LoggerUtils() {
		super();
	}
	 
	/**
     * 获取客户端ip地址
     * @param request
     * @return
     */
	public static String getClientIp(HttpServletRequest request) {
		//X-Forwarded-For(XFF)是用来识别通过HTTP代理或负载均衡方式连接到Web服务器的客户端最原始的IP地址的HTTP请求头字段。
		String ip = request.getHeader("x-forwarded-for");
		if(ip==null || ip.trim()==null || "unknown".equalsIgnoreCase(ip)) {
			//Proxy-Client-IP/WL- Proxy-Client-IP 这个一般是经过apache http服务器的请求才会有，
			//用apache http做代理时一般会加上Proxy-Client-IP请求头，而WL- Proxy-Client-IP是他的weblogic插件加上的头
			ip = request.getHeader("Proxy-Client-Ip"); 
		}
		if(ip==null || ip.trim()==null || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP"); 
			
		}
		if(ip==null || ip.trim()==null || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
	     //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割   
		// 多个路由时，取第一个非unknown的ip
		final String[] arr = ip.split(",");
		for (final String str : arr) {
			if(!"unknown".equalsIgnoreCase(str)) {
				ip=str;
				break;
			}
		}
		return ip;
	}
    /**
     * 判断是否为ajax请求
     * 可以用来判断客户端的请求是Ajax请求还是其他请求。。
     *  若 req.headers['x-requested-with'].toLowerCase() == 'xmlhttprequest' 则为ajax请求
     * @param request
     * @return
     */
	public static String getRequestType(HttpServletRequest request) {
		return request.getHeader("x-requested-with");
		
	}
}
