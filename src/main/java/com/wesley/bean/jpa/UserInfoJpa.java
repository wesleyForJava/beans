package com.wesley.bean.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wesley.bean.pojo.UserInfoEntity;

public interface UserInfoJpa extends JpaRepository<UserInfoEntity, String>,JpaSpecificationExecutor<UserInfoEntity>{

}
