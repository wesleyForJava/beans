package com.wesley.bean.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.wesley.bean.pojo.User;

/**
 * CrudRepository
 *该接口内包含了最简单的CRUD也就是Create、Read、Update、Delete方法，当然还有count、exists方法
 * @author pc
 *PagingAndSortingRepository
 *该接口继承自CrudRepository接口，包含了最基本的CRUD方法的实现，该接口内部添加了两个方法
 *QueryByExampleExecutor
 */
public interface UserJPA extends JpaRepository<User, String>,CrudRepository<User, String>,PagingAndSortingRepository<User, String>{
	  @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
	    User findByUsernameCaseInsensitive(@Param("username") String username);
	  
	}
