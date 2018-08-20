package com.wesley.bean.service;


import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.wesley.bean.bean.UserBean;
import com.wesley.bean.event.UserRegisiterEvent;

@Service
public class UserService {
	
	//@Autowired
	@Inject
	private ApplicationContext applicationContext;
	
	
	public void regisiter(UserBean userBean) {
		this.applicationContext.publishEvent(new UserRegisiterEvent(this, userBean));
	}

}
