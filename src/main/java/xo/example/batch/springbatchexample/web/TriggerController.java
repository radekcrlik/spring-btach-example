package xo.example.batch.springbatchexample.web;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TriggerController {

	final Job batchJob;
	final JobLauncher launcher;

	public TriggerController(final JobLauncher launcher, final @Qualifier("exampleJob") Job batchJob) {
		this.launcher = launcher;
		this.batchJob = batchJob;
	}

	@PostMapping("/trigger")
	public String triggerBatchJob() {
		final var builder = new JobParametersBuilder();
		try {
			final JobParameters params = builder
					.addString("time", LocalDateTime.now().toString())
					.toJobParameters();

			launcher.run(batchJob, params);
		} catch (final JobExecutionException e) {
			return "{\"status\": 0}\n";
		}

		return "{\"status\": 1}\n";
	}
}
