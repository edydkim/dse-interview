package com.app_creative.poc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eddie Kim
 * @project dse-interview
 * @package com.app_creative.poc.controller
 */
@RestController
@RequestMapping(value = "/jobs")
public class JobController {
    private final Logger looger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @RequestMapping(value = "/run")
    public String runJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            looger.error("Error while running job", e);
        }

        return "Job run finished...";
    }
}
