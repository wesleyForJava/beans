package com.wesley.bean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wesley.bean.jpa.UserAuthJPA;
import com.wesley.bean.pojo.UserAuthor;

public class UserAuthService implements UserDetailsService{
    @Autowired
    UserAuthJPA userAuthJPA;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserAuthor user = userAuthJPA.findByUsername(username);
		if(user==null) {
			throw new RuntimeException("未查询到用户"+username+"的信息!");
		}
		return user;
	}
}
