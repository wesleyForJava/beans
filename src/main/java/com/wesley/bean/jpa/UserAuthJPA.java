package com.wesley.bean.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wesley.bean.pojo.UserAuthor;

public interface UserAuthJPA extends JpaRepository<UserAuthor, Long>{
//使用springdata jpa方法定义查询
	public UserAuthor findByUsername(String username);
}
