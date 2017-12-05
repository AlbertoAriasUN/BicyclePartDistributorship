package FileGeneration;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import BicyclePartDistributorshipAPI.Models.BicyclePart;
import BicyclePartDistributorshipAPI.Models.InvoiceSaleRecord;
import Main.APICaller;

public class InvoiceTXTGenerator implements IInvoiceGenerator{

	@Override
	public void generateInvoice(String invoiceName, String employeeName) throws IOException {
		String outputFilepath = "Output/Invoices/" + invoiceName + ".txt";
    	
    	try(FileWriter writer = new FileWriter(outputFilepath)) {
    		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    		ArrayList<InvoiceSaleRecord> records = APICaller.getInvoiceController(invoiceName).getSaleRecordList();
    		double total = 0;
    		
    		writer.write("Sales invoice generated by " + APICaller.getLoggedInUser() + " @ ");
    		writer.write(format.format(records.get(0).getDatetime()) + "\n");
    		writer.write("--------------------\n");
    		writer.write("Part Name\t Part Number\t List Price\t Sale Price\t Quantity\t Total Cost\n");
    		for(InvoiceSaleRecord record : records) {
    			BicyclePart part = APICaller.getPartController().getPart(record.getPartNumber());
    			writer.write(part.getPartName() + "\t " + part.getPartNumber() + "\t " + part.getListPrice() + "\t " +
    						 part.getSalePrice() + "\t " + record.getQuantity() + "\t " + 
    						 record.getQuantity() * part.getPrice() + "\n");
    			total += (part.getPrice() * record.getQuantity());
    		}
    		writer.write("--------------------\n");
    		writer.write("Total Sale: $" + total + "\n\n");
    		writer.write("Provided to " +  employeeName);
    		
    		if(Desktop.isDesktopSupported()) {
    			new Thread(() -> {
						try {
							Desktop.getDesktop().open(new File(outputFilepath));
						} catch (IOException e) {
							//
						}
    			}).start();
    		}
    	}
	}
}
