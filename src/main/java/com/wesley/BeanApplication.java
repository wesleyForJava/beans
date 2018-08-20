package com.wesley;

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
import com.wesley.bean.context.ApplicationContextProvider;
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
	     @Bean
	    public ExitCodeGenerator exitCodeGenerator() {
	        return () -> 42;
	    }

	public static void main(String [] args) {
		SpringApplication springApplication=new SpringApplication(BeanApplication.class);
		springApplication.addListeners(new MyListener());
		springApplication.addInitializers(new MyApplicationiniter());
		ConfigurableApplicationContext context = springApplication.run(args);
		System.out.println(context.getBean(Properties.class).toString());
		System.out.println(context.getBean(Animal.class));
		System.out.println(context.getBean(InitBeanAndDestroyBean.class));
		String property = context.getEnvironment().getProperty("url");
		System.out.println(context.getEnvironment().getProperty("password"));
		System.out.println(property);
		springApplication.setAdditionalProfiles("test","dev");
		System.out.println(context.getBean(UserBean.class));
		UserBean bean = ApplicationContextProvider.getBean(UserBean.class);
		bean.setName("你好");
		//System.out.println(context.getBean(MyBeanNotOfRequiredTypeFailureAnalyzer.class));
		System.out.println(bean.getName());
		System.out.println(context.getBean(SessionConfiguration.class));
		System.out.println(context.getBean(JWTConfiguration.class));
		System.out.println(context.getBean(LoggerConfguration.class));
/*		System.exit(SpringApplication
				.exit(SpringApplication.run(BeanApplication.class, args)));*/
	}
}
