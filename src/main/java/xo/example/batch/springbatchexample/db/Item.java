package xo.example.batch.springbatchexample.db;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@Tsid
	Long id;

	private String input;

	private String output;

	public Item() {}

	public Item(
			final String input,
			final String output
	) {
		this.input = input;
		this.output = output;
	}

	public Item(
			final Long id,
			final String input,
			final String output
	) {
		this(input, output);

		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
