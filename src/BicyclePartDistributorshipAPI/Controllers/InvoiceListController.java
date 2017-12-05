package BicyclePartDistributorshipAPI.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;
import BicyclePartDistributorshipAPI.Models.InvoiceSaleRecord;
import Database.Database;
import Database.DatabaseListModel;

public class InvoiceListController {
	
	private DatabaseConnection dbConnection;
	
	public InvoiceListController() {
		dbConnection = new DatabaseConnection();
	}
	
	public void addInvoice(String name) throws IOException {
		String filepath = "Data/Invoices/" + name + ".txt";
		dbConnection.getInvoiceListDB().addValue(new DatabaseListModel(filepath));
	}
	
	public ArrayList<String> getInvoiceNames() throws IOException {
		Map<String, Database<InvoiceSaleRecord>> map = dbConnection.getInvoiceMap();
		ArrayList<String> invoiceNames = new ArrayList<>();
		map.forEach((k,v) -> invoiceNames.add(k));
		return invoiceNames;
	}
}
