package com.wesley.bean.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wesley.bean.pojo.Authority;

public interface AuthorityJPA extends JpaRepository<Authority, String>{
	

}
