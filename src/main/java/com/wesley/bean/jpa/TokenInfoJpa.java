package com.wesley.bean.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wesley.bean.pojo.TokenInfoEntity;

public interface TokenInfoJpa extends JpaRepository<TokenInfoEntity, Long>,JpaSpecificationExecutor<TokenInfoEntity>{

}
