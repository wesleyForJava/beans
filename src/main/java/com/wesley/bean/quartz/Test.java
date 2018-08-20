package com.wesley.bean.quartz;

import java.util.Date;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import com.wesley.bean.job.SimpleJob;

public class Test {
	public static void main(String[] args) throws SchedulerException {
		// 获取一个调度工厂
        SchedulerFactory schedFact = new StdSchedulerFactory();

        // 获取一个调度器
        Scheduler sched = schedFact.getScheduler();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
        // 在当前时间15秒后运行
        Date startTime = DateBuilder.nextGivenSecondDate(new Date( ),15);
        // 创建一个SimpleTrigger实例，指定该Trigger在Scheduler中所属组及名称。
        // 接着设置调度的时间规则.当前时间15秒后运行，每10秒运行一次，共运行5次
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)
                        .withRepeatCount(2)
                )
                .build();
        sched.scheduleJob(job, trigger);
        // 调度启动
        sched.start();
	}
	
	
	  public static void main1(String[] args) throws SchedulerException {
	        SchedulerFactory schedFact = new StdSchedulerFactory();

	        // 获取一个调度器
	        Scheduler sched = schedFact.getScheduler();
	        JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job2", "group2").build();
	        // 每两秒执行
	        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2").withSchedule(
	                CronScheduleBuilder.cronSchedule("/2 * * * * ?")
	        ).build();
	        sched.scheduleJob(job, trigger);
	        // 调度启动
	        sched.start();

	    }
	  
	  
	  
	  
	  
	  

	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

}
