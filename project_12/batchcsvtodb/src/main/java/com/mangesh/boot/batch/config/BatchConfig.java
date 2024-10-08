package com.mangesh.boot.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.mangesh.boot.batch.model.Product;

@Configuration
public class BatchConfig {
	
	@Autowired
	DataSource dataSource;
	
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public Job job()
	{
		return new JobBuilder("job-1",jobRepository).flow(step()).end().build();
		
	}
	
	@Bean
	public Step step()
	{
		StepBuilder stepBuilder = new StepBuilder("step-1", jobRepository);
		return stepBuilder.<Product,Product>chunk(0, transactionManager).reader(reader())
		.processor(processor()).writer(writer()).build();
		
		
	}

	@Bean
	public ItemReader<Product> reader() {
		FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("products.csv"));

		DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("id", "name", "description", "price");
		BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Product.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		reader.setLineMapper(lineMapper);
		return reader;

	}
	
	@Bean
	public ItemProcessor<Product,Product> processor() {
		
		return (p)->{
			p.setPrice(p.getPrice()-p.getPrice()*10/100);
			return p;
		};

	}
	@Bean
	public ItemWriter<Product> writer()
	{
		JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
		
		writer.setDataSource(dataSource);
		
		writer.setItemSqlParameterSourceProvider(
				new BeanPropertyItemSqlParameterSourceProvider<Product>());
		
		writer.setSql("INSERT INTO PRODUCT(ID,NAME,DESCRIPTION,PRICE) VALUES(:id,:name,:description,:price)");
		return writer;
		
	}

}
