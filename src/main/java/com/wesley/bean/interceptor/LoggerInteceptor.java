package com.wesley.bean.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wesley.bean.jpa.LoggerJPA;
import com.wesley.bean.pojo.LoggerEntity;
import com.wesley.bean.util.LoggerUtils;

public class LoggerInteceptor implements HandlerInterceptor{
	   //请求开始时间标识
    private static final String LOGGER_SEND_TIME = "_send_time";
    //请求日志实体标识
    private static final String LOGGER_ENTITY = "_logger_entity";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 创建日志实体
		LoggerEntity log= new LoggerEntity();
		// 获取请求的SessionID
	    String sessionID=request.getRequestedSessionId();
		// 请求路径
	    String url=request.getRequestURI();
		// 获取请求的参数信息
	  String paramData=JSON.toJSONString(request.getParameterMap(),
	    		SerializerFeature.DisableCircularReferenceDetect,
	    		SerializerFeature.WriteMapNullValue
	    		);
		// 设置客户端IP
	    log.setClientIp(LoggerUtils.getClientIp(request));
		// 设置请求方法
	    log.setMethod(request.getMethod());
		// 设置请求类型（json|普通请求）
        log.setType(LoggerUtils.getRequestType(request));		
	    // 设置请求参数内容json字符串
        log.setParamData(paramData);
	    // 设置请求地址
        log.setUri(url);
	    // 设置sessionId
        log.setSessionId(sessionID);
	    // 设置请求开始时间
		request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());
	    // 设置请求实体到request内，方便afterCompletion方法调用
		request.setAttribute(LOGGER_ENTITY, log);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
       //获取请求错误码
        int status = response.getStatus();
        //当前时间
		long currentTime = System.currentTimeMillis();
        //请求开始时间
		Long time = Long.valueOf(request.getAttribute(LOGGER_SEND_TIME).toString());
        //获取本次请求日志实体
		LoggerEntity loggerEntity = (LoggerEntity) request.getAttribute(LOGGER_ENTITY);
        //设置请求时间差
         loggerEntity.setTimeConsuming(Integer.valueOf(currentTime-time+""));
        //设置返回时间
          loggerEntity.setReturnTime(currentTime+"");
        //设置返回错误码
          loggerEntity.setHttpStatusCode(status+"");
        //设置返回值
          loggerEntity.setReturnData(
         JSON.toJSONString(request.getAttribute(LoggerUtils.LOGGER_RETURN)
        		 ,SerializerFeature.DisableCircularReferenceDetect,
        		 SerializerFeature.WriteMapNullValue
        		 ));
        //执行将日志写入数据库
          LoggerJPA logDao = getDao(LoggerJPA.class, request);
          logDao.save(loggerEntity);
	}

	
	  /**
     * 根据传入的类型获取spring管理的对应dao
     * @param clazz 类型
     * @param request 请求对象
     * @param <T>
     * @return
     */
	private <T> T getDao(Class<T> clazz, HttpServletRequest request) {
		BeanFactory bean = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
		return bean.getBean(clazz);
	}
}
