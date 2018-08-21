package com.wesley.bean.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.jpa.impl.JPAQuery;
import com.wesley.bean.jpa.GoodInfoJPA;
import com.wesley.bean.pojo.GoodInfoBean;
import com.wesley.bean.pojo.QGoodInfoBean;
import com.wesley.bean.util.Inquirer;

@RestController
public class QueryController {
	@Autowired
	GoodInfoJPA goodInfo;
	
	//注入EntityManager
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@RequestMapping(value="/query" ,produces="application/json;charset=utf-8")
	public List<GoodInfoBean> list(){
		//qsdl查询实体
		QGoodInfoBean goodInfoBean=QGoodInfoBean.goodInfoBean;
        //构建JPA查询对象
		JPAQuery<QGoodInfoBean> jpaQuery=new JPAQuery<>(entityManager);
		//返回查询接口
		 return jpaQuery
		.select(goodInfoBean)
		.from(goodInfoBean)
		.where(goodInfoBean.typeId.eq(Long.valueOf("1")))
		//返回结果
		.fetch();
	}
	@RequestMapping(value="/queryjoin" ,produces="application/json;charset=utf-8")
	public List<GoodInfoBean> join(){
		//qsdl查询实体
		QGoodInfoBean goodInfoBean=QGoodInfoBean.goodInfoBean;
	   //自定义查询对象
		Inquirer inquirer=new Inquirer();
		//添加查询条件
		inquirer.put(goodInfoBean.typeId.eq(Long.valueOf("1")));
		return inquirer.iteratortolist(goodInfo.findAll(inquirer.buidleQuery()));
	}
}
