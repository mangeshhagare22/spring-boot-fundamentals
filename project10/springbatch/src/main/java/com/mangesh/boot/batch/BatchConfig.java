package com.mangesh.boot.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public Job job()
	{
		return new JobBuilder("job-1",jobRepository).start(step()).listener(listner()).build();
		
	}
	
	@Bean
	public Step step()
	{
		StepBuilder stepBuilder = new StepBuilder("step-1", jobRepository);
		return stepBuilder.<String,String>chunk(0, transactionManager).reader(reader())
		.processor(processor()).writer(writer()).build();
		
		
	}
	
	@Bean
	public Reader reader()
	{
		return new Reader();
	}
	@Bean
	public Writer writer()
	{
	   return new Writer();
	}
	@Bean
	public Processor processor()
	{
		return new Processor();
	}
	
	@Bean
	public MyJobListner listner()
	{
		return new MyJobListner();
	}

}
