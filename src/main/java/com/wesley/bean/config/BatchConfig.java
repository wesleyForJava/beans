package com.wesley.bean.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.wesley.bean.pojo.User;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Bean
	public JobRepository jobRepository(DataSource datasource,PlatformTransactionManager transactionManager) throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean=new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDataSource(datasource);
		jobRepositoryFactoryBean.setTransactionManager(transactionManager);
		jobRepositoryFactoryBean.setDatabaseType("MySql");
		return jobRepositoryFactoryBean.getObject();
	}
	
	@Bean
	public SimpleJobLauncher jobLauncher(DataSource datasource,PlatformTransactionManager transactionManager) throws Exception {
		SimpleJobLauncher jobLauncher=new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository(datasource, transactionManager));
		return jobLauncher;
	}
	
	
	@Bean
	public Job importJob(JobBuilderFactory jobs,Step s1) {
		return (Job)jobs.get("importJob").incrementer(new RunIdIncrementer()).flow(s1).build();
	}
	
	@Bean
	public Step step(StepBuilderFactory factory,ItemReader<User> reader,ItemWriter<User> writer) {
		return null;
	}
}
