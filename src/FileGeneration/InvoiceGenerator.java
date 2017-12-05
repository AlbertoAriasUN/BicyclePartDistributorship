package FileGeneration;

import java.io.IOException;

public class InvoiceGenerator {
	private IInvoiceGenerator generator;
	
	public InvoiceGenerator(IInvoiceGenerator generator) {
		this.generator = generator;
	}
	
	public void generateInvoice(String invoiceName, String employeeName) throws IOException {
		generator.generateInvoice(invoiceName, employeeName);
	}
}
