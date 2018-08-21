package com.wesley.bean.beandefinition;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.wesley.bean.bean.UserBean;
public class ImportBeanDefinitionRefisters implements ImportBeanDefinitionRegistrar{

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		BeanDefinitionBuilder rbd = BeanDefinitionBuilder.rootBeanDefinition(UserBean.class);
		BeanDefinition beanDefinition = rbd.getBeanDefinition();
		registry.registerBeanDefinition("user", beanDefinition);
	}

}
