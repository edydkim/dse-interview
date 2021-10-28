package com.app_creative.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.MessageFormat;

/**
 * @author Eddie Kim
 * @project dse-interview
 * @package com.app_creative.poc
 */
@SpringBootApplication
@EnableScheduling
public class Application {
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Scheduled(cron = "0 */10 * * * ?")
    public void perform() {
        String jobID = String.valueOf(System.currentTimeMillis());
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", jobID)
                .toJobParameters();
        try {
            jobLauncher.run(job, params);
        } catch (Exception e) {
            logger.error(MessageFormat.format("Error [{0}]:",jobID), e.getMessage());
        }
    }
}
