package xo.example.batch.springbatchexample.batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import xo.example.batch.springbatchexample.db.Item;

@Service
@Qualifier("cli-writer")
public class CliWriter implements ItemWriter<Item> {
	@Override
	public void write(Chunk<? extends Item> chunk) throws Exception {
		for (final var i : chunk) {
			System.out.println("Item: " + i.getOutput());
		}
	}
}
