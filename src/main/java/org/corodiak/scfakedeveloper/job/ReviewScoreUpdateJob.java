package org.corodiak.scfakedeveloper.job;

import org.corodiak.scfakedeveloper.repository.ReviewRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReviewScoreUpdateJob extends QuartzJobBean {

	private final ReviewRepository reviewRepository;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		reviewRepository.batchUpdateReviewScore();
	}
}
