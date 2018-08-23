package com.wesley.bean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesley.bean.dto.GoodInfoDTO;
import com.wesley.bean.jpa.GoodInfoJPA;
import com.wesley.bean.jpa.GoodTypeJPA;
import com.wesley.bean.mapper.GoodInfoMapper;
import com.wesley.bean.pojo.GoodInfoBean;
import com.wesley.bean.pojo.GoodTypeBean;

@RestController
public class GoodInfoController {
	/**
     * 注入商品基本信息jpa
     */
    @Autowired
    private GoodInfoJPA goodInfoJPA;
    /**
     * 注入商品类型jpa
     */
    @Autowired
    private GoodTypeJPA goodTypeJPA;
    /**
     * 注入mapStruct转换Mapper
     */
    @Autowired //会报错是没有compiler会报错 没有编译是无法注入实现类的。
    private GoodInfoMapper goodInfoMapper;
    
    @RequestMapping(value="/detail/{id}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GoodInfoDTO detail(@PathVariable("id") Long id) {
    	//查询商品的基本信息
    	GoodInfoBean goodInfoBean = goodInfoJPA.findOne(id);
    	//查询商品类型的基本信息
    	GoodTypeBean goodTypeBean = goodTypeJPA.findOne(goodInfoBean.getTypeId());
    	//返回转换Dto
		return goodInfoMapper.from(goodInfoBean, goodTypeBean);
    }

}
