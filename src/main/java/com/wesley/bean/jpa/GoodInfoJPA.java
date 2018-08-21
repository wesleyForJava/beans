package com.wesley.bean.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.wesley.bean.pojo.GoodInfoBean;

public interface GoodInfoJPA extends JpaRepository<GoodInfoBean, Long>,QueryDslPredicateExecutor<GoodInfoBean>{

}
