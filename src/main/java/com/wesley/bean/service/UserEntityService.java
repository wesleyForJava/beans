package com.wesley.bean.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wesley.bean.jpa.UserEntityJpa;
import com.wesley.bean.pojo.UserEntity;
/**
 * @CacheConfig：该注解是用来开启声明的类参与缓存,如果方法内的@Cacheable注解没有添加key值，
 * 那么会自动使用cahceNames配置参数并且追加方法名。
 * @Cacheable：配置方法的缓存参数，可自定义缓存的key以及value。
 * @author pc
 *
 */
@Service
@CacheConfig(cacheNames="user")
public class UserEntityService {
   @Autowired
   UserEntityJpa userEntityJpa;
   
   @Cacheable
   public List<UserEntity> list(){
	return userEntityJpa.findAll();
   }
}
