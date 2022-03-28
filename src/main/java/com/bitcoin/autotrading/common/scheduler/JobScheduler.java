package com.bitcoin.autotrading.common.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Slf4j
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='true'")
@RequiredArgsConstructor
public class JobScheduler {

    private final ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        log.info("scheduler start");
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory(){

        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        log.info("Configuring JobFactory");

        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactoryBean factory) throws SchedulerException{

        log.info("Getting a handle to the Scheduler");
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(job,trigger);
        log.info("Starting Scheduler");
        scheduler.start();

        return scheduler;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException{
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(springBeanJobFactory());
        return factory;
    }

    @Bean
    public JobDetail jobDetail(){
        return  JobBuilder.newJob().ofType(SampleJob.class)
                .storeDurably()
                .withIdentity(JobKey.jobKey("Qrtz_Job_Detail"))
                .withDescription("Invoke Sample Job Service")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job){

        int frequencyInSec = 10;
        log.info(String.valueOf(frequencyInSec));

        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity(TriggerKey.triggerKey("Qrtz_Trigger"))
                .withDescription("Sample Trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(frequencyInSec).repeatForever())
                .build();
    }

}
