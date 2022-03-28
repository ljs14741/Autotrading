package com.bitcoin.autotrading.common.scheduler;

import com.bitcoin.autotrading.common.scheduler.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SampleJob implements Job {

    private final SampleService jobService;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());

        jobService.executeSampleJob();

        log.info("Next job scheduled @ {}", context.getNextFireTime());
    }
}
