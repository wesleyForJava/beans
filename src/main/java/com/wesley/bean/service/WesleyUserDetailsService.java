package com.wesley.bean.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.wesley.bean.exception.NewUserNotFoundException;
import com.wesley.bean.jpa.UserJPA;
import com.wesley.bean.pojo.Authority;
import com.wesley.bean.pojo.User;


@Component("userDetailsService")
public class WesleyUserDetailsService implements UserDetailsService{
    
	@Autowired
	private UserJPA userRespority;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
		String lowerCaseLogin = login.toLowerCase();
		User userFromDataBase = userRespority.findByUsernameCaseInsensitive(lowerCaseLogin);
		if (userFromDataBase == null) {
			throw new NewUserNotFoundException("User" + lowerCaseLogin + "is not found in databases;");
		}
		// 获取用户的所有权限并且SpringSecurity需要的集合
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		for (Authority authority : userFromDataBase.getAuthorities()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
			grantedAuthorities.add(grantedAuthority);
		}
		 //返回一个SpringSecurity需要的用户对象
		return new org.springframework.security.core.userdetails.User(userFromDataBase.getUsername(),
				userFromDataBase.getPassword(), grantedAuthorities);
	}

}
