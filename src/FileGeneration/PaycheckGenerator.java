package FileGeneration;

import java.io.IOException;
import java.time.Instant;

public class PaycheckGenerator {
	private IPaycheckGenerator generator;
	
	public PaycheckGenerator(IPaycheckGenerator generator) {
		this.generator = generator;
	}
	
	public void generatePaycheck(String associateName, Instant beginDate, Instant endDate) throws IOException {
		generator.generatePaycheck(associateName, beginDate, endDate);
	}
}
