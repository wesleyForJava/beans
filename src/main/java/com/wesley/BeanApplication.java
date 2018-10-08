package com.wesley;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.wesley.bean.bean.UserBean;
import com.wesley.bean.beandefinition.ImportBeanDefinitionRefisters;
import com.wesley.bean.config.JWTConfiguration;
import com.wesley.bean.config.LoggerConfguration;
import com.wesley.bean.config.SessionConfiguration;
import com.wesley.bean.init.Animal;
import com.wesley.bean.init.InitBeanAndDestroyBean;
import com.wesley.bean.init.MyApplicationiniter;
import com.wesley.bean.listener.MyListener;
import com.wesley.bean.properties.Properties;

@SpringBootApplication()
@ComponentScan(basePackages= {"com.wesley.bean"})
@Import({ImportBeanDefinitionRefisters.class})
@EnableScheduling
public class BeanApplication {
	private static Logger logger =LoggerFactory.getLogger(BeanApplication.class);
	     @Bean
	    public ExitCodeGenerator exitCodeGenerator() {
	        return () -> 42;
	    }

	public static void main(String [] args) {
		SpringApplication springApplication=new SpringApplication(BeanApplication.class);
		springApplication.addListeners(new MyListener());
		springApplication.addInitializers(new MyApplicationiniter());
		ConfigurableApplicationContext context = springApplication.run(args);
		logger.info("{}"+context.getBean(Properties.class).toString());
		logger.info("{}"+context.getBean(Animal.class));
		logger.info("{}"+context.getBean(InitBeanAndDestroyBean.class));
		String property = context.getEnvironment().getProperty("url");
		logger.info("{}"+context.getEnvironment().getProperty("password"));
		logger.info("{}"+property);
		springApplication.setAdditionalProfiles("test","dev");
		logger.info("{}"+context.getBean(UserBean.class));
		//logger.info("{}"+context.getBean(MyBeanNotOfRequiredTypeFailureAnalyzer.class));
		logger.info("{}"+context.getBean(SessionConfiguration.class));
		logger.info("{}"+context.getBean(JWTConfiguration.class));
		logger.info("{}"+context.getBean(LoggerConfguration.class));
/*		System.exit(SpringApplication
				.exit(SpringApplication.run(BeanApplication.class, args)));*/
	}
}
