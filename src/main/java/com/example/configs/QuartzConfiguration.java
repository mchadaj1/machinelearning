package com.example.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

/**
 * Klasa konfigurująca wykonywanie cykliczne wykonywania Cron Taska.
 */
@Configuration
@ComponentScan("Quartz")
public class QuartzConfiguration {
	@Bean
	public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
		MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
		obj.setTargetBeanName("algorithmExecutor");
		obj.setTargetMethod("lookForPendingTask");
		return obj;
	}
	@Bean
	public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
		SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
		stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setRepeatInterval(50000);//50 sekund
		return stFactory;
	}
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(simpleTriggerFactoryBean().getObject());
		return scheduler;
	}
}  
 