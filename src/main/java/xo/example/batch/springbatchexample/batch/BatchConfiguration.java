package xo.example.batch.springbatchexample.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;
import xo.example.batch.springbatchexample.db.Item;
import xo.example.batch.springbatchexample.db.ItemRepository;

import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Bean
	@Qualifier("example-job")
	public Job exampleJob(
			final JobRepository jobRepository,
			final @Qualifier("item-main-step") Step mainStep) {
		return new JobBuilder("footballJob", jobRepository)
				.start(mainStep)
				.build();
	}

	@Bean
	@Qualifier("item-main-step")
	public Step itemMainStep(
			final JobRepository jobRepository,
			final PlatformTransactionManager transactionManager,
			final @Qualifier("infoReader") ItemReader<Item> itemReader,
			final @Qualifier("cli-writer") ItemWriter<Item> itemWriter,
			final DbItemProcessor itemProcessor) {
		return new StepBuilder("item-step", jobRepository)
				.<Item, Item>chunk(10, transactionManager)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
	}

	@Bean(name = "infoReader")
	@StepScope
	public RepositoryItemReader<Item> reader(final ItemRepository itemRepository) {
		final RepositoryItemReader<Item> reader = new RepositoryItemReader<>();
		reader.setRepository(itemRepository);
		reader.setMethodName("findAll");
		reader.setSort(Map.of("id", Sort.Direction.ASC));
		return reader;
	}
}
