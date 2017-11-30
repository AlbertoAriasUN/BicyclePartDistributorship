package BicyclePartDistributorshipAPI.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;
import BicyclePartDistributorshipAPI.Models.InvoiceSaleRecord;

/**
 *
 * @author colinvitkus
 */
public class InvoiceController {
    
	private DatabaseConnection dbConnection;
	
	private String name;
 
    public InvoiceController(String name) { 
        dbConnection = new DatabaseConnection();
        this.name = name; 
    }
    
    public void addSale(InvoiceSaleRecord sale) throws IOException {
    	dbConnection.getInvoiceDB(name).addValue(sale);
    }
    
    public ArrayList<InvoiceSaleRecord> getInvoiceSaleRecordList() throws IOException {
    	return dbConnection.getInvoiceDB(name).getValuesList();
    }
    
    public boolean containsRecordID(String id) throws IOException {
    	return dbConnection.getInvoiceDB(name).getValue(id) != null;
    }
}