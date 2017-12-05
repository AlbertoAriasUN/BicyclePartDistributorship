package FileGeneration;

import java.io.IOException;

public interface IInvoiceGenerator {
	public void generateInvoice(String invoiceName, String employeeName) throws IOException;
}
