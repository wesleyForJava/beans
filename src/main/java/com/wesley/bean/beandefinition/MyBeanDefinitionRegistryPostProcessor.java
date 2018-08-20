package com.wesley.bean.beandefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import com.wesley.bean.init.Animal;
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		   for (int i = 0; i < 10; i++) {
			   BeanDefinitionBuilder dbs = BeanDefinitionBuilder.rootBeanDefinition(Animal.class);
			   dbs.addPropertyValue("name", "admin"+i);
			   registry.registerBeanDefinition("Animail"+i, dbs.getBeanDefinition());
		}
	}

}
