package FileGeneration;

import java.io.IOException;
import java.time.Instant;

public interface IPaycheckGenerator {
	public void generatePaycheck(String associate, Instant beginDate, Instant endDate) throws IOException;
}
