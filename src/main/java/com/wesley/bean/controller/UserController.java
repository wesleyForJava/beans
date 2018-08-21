package com.wesley.bean.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wesley.bean.bean.UserBean;
import com.wesley.bean.jpa.UserEntityJpa;
import com.wesley.bean.pojo.UserEntity;
import com.wesley.bean.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	//用户业务逻辑实现
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserEntityJpa userEntityJpa;
    /**
     * 注册控制方法
     * @param user
     * @return
     */
	@RequestMapping(value="/register")
	public String regisiter(UserBean user) {
	  //调用注册业务逻辑
		userService.regisiter(user);
		return "注册成功!";
	}
	
	 /**
     * 查询用户列表方法
     * @return
     */
    @RequestMapping(value ="/list" ,method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<UserEntity> list(){
        return userEntityJpa.findAll();
    }
    
    /**
     * 查询用户列表方法
     * @return
     */
    @RequestMapping(value ="/age" ,method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<UserEntity> age(){
    	return userEntityJpa.nativeQuery(20);
    }

    /**
     * 添加、更新用户方法
     * @param entity
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String save(UserEntity entity)
    {
    	@SuppressWarnings("unused")
		UserEntity userEntity=new UserEntity();
    	entity.setAddress("中国北京");
    	entity.setAge(11);
    	entity.setName("王龙");
    	userEntityJpa.save(entity);
    	return "用户添加信息成功!";
    }

    /**
     * 删除用户方法
     * @param id 用户编号
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public List<UserEntity> delete(Long id)
    {
        userEntityJpa.delete(id);
        return userEntityJpa.findAll();
    }
    /**
     * 删除用户方法
     * @param id 用户编号
     * @return
     */
    @RequestMapping(value = "/deleteByName",method = RequestMethod.GET)
    public String deleteByName(String name, String password)
    {
    	userEntityJpa.deleteByNameAndPassword("ada", "2");
    	return "自己义删除成功！";
    }
    
    @RequestMapping(value = "/cutpage",produces="application/json; charset=utf-8") 
    public List<UserEntity> cutPage(int page)
    {
    	UserEntity userEntity=new UserEntity();
    	userEntity.setPage(page);
    	userEntity.setSize(2);
    	userEntity.setSord("desc");
    	//获取排序对象
    	Sort.Direction sort_direction = Sort.Direction.ASC.toString().equalsIgnoreCase(userEntity.getSord()) ? Sort.Direction.ASC : Sort.Direction.DESC;
    	//设置排序对象参数
    	Sort sort=new Sort(sort_direction,userEntity.getSidx());
    	//创建分页对象
    	PageRequest pageRequest=new PageRequest(userEntity.getPage()-1, userEntity.getSize(), sort);
    	Page<UserEntity> findAll = userEntityJpa.findAll(pageRequest);
		return findAll.getContent();
    }
}
