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
    
    public ArrayList<InvoiceSaleRecord> getSaleRecordList() throws IOException {
    	return dbConnection.getInvoiceDB(name).getValuesList();
    }
    
    public InvoiceSaleRecord getSaleRecord(long partNumber) throws IOException {
    	return dbConnection.getInvoiceDB(name).getValue(partNumber);
    }
    
    public void addSaleRecord(InvoiceSaleRecord record) throws IOException {
    	dbConnection.getInvoiceDB(name).addValue(record);
    }
}