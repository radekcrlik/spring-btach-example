package xo.example.batch.springbatchexample.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import xo.example.batch.springbatchexample.db.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DbItemProcessor implements ItemProcessor<Item, Item> {

	final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("H:m:s");

	@Override
	public Item process(Item item) throws Exception {
		final var now = LocalDateTime.now();
		item.setOutput(item.getInput() + " - " + now.format(dtFormatter));

		return item;
	}
}
