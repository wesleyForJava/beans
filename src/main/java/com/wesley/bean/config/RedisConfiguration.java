package com.wesley.bean.config;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport{

	/**
	 * 自定义生成key的规则
	 */
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				//格式化缓存key字符串
				StringBuilder sbBuilder=new StringBuilder();
				//追加类名
				sbBuilder.append(target.getClass().getName());
				//追加方法名
				sbBuilder.append(method.getName());
				//遍历参数并且追加
				for (Object object : params) {
					sbBuilder.append(object.toString());
				}
				System.out.println("调用redis缓存的key"+sbBuilder.toString());
				return sbBuilder.toString();
			}
		};
		
	}
    /**
     * 采用RedisCacheManager作为缓存管理器
     * @param redisTemplate
     * @return
     */
	@Bean
	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}
}
