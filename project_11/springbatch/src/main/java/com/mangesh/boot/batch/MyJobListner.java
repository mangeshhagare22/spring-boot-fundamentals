package com.mangesh.boot.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobListner implements JobExecutionListener {
	
 public void beforeJob(JobExecution jobExecution)
 {
	 System.out.println("Job Started");
 }
 public void afterJob(JobExecution jobExecution)
 {
	 System.out.println("Job Ended "+jobExecution.getStatus().toString());
 }


}
