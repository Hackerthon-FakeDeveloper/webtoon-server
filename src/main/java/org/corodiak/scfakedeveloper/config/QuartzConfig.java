package org.corodiak.scfakedeveloper.config;

import javax.annotation.PostConstruct;

import org.corodiak.scfakedeveloper.job.ReviewScoreUpdateJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@AllArgsConstructor
@Slf4j
public class QuartzConfig {

	private final Scheduler scheduler;

	@PostConstruct
	public void start() {
		JobDetail jobDetail = buildJobDetail(ReviewScoreUpdateJob.class);
		try {
			scheduler.scheduleJob(jobDetail, buildJobTrigger("5 * * * * ?"));
		} catch (SchedulerException ignored) {
			log.error("Scheduler Error");
		}
	}

	public Trigger buildJobTrigger(String scheduleExp) {
		return TriggerBuilder.newTrigger()
			.withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
	}

	public JobDetail buildJobDetail(Class job) {

		return JobBuilder.newJob(job).build();
	}

}
