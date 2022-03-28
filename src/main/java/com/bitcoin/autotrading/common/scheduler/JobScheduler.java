package com.bitcoin.autotrading.common.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobScheduler {


    public void execute(JobExecutionContext context) throws JobExecutionException{
        log.debug("job execute");

    }
}
