package xo.example.batch.springbatchexample.extra;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import xo.example.batch.springbatchexample.db.Item;
import xo.example.batch.springbatchexample.db.ItemRepository;

import java.util.stream.IntStream;

@Service
public class AppRunner implements CommandLineRunner {

	private final ItemRepository itemRepository;

	public AppRunner(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		if (itemRepository.count() > 0) {
			return;
		}

		IntStream.range(1, 100).forEach(i -> {
			final var item = new Item("item nr." + i, "");

			itemRepository.save(item);

		});
	}
}
