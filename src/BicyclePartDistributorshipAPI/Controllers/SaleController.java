package BicyclePartDistributorshipAPI.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;
import BicyclePartDistributorshipAPI.Models.SaleRecord;

public class SaleController {
	private DatabaseConnection dbConnection;
	
	public SaleController() {
		dbConnection = new DatabaseConnection();
	}
	
	public SaleRecord getSale(String id) throws IOException {
		return dbConnection.getSaleRecordsDB().getValue(id);
	}
	
	public ArrayList<SaleRecord> getSalesList() throws IOException {
		return dbConnection.getSaleRecordsDB().getValuesList();
	}
	
	public void addSale(SaleRecord sale) throws IOException {
		dbConnection.getSaleRecordsDB().addValue(sale);
	}
}
