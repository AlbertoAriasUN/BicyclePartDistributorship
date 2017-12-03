package BicyclePartDistributorshipAPI.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;
import BicyclePartDistributorshipAPI.Models.SalesVan;

public class SalesVanController {
	
	private DatabaseConnection dbConnection;
	
	public SalesVanController() {
		this.dbConnection = new DatabaseConnection();
	}
	
	public ArrayList<SalesVan> getSalesVanList() throws IOException {
		return dbConnection.getSalesVanDB().getValuesList();
	}
	
	public SalesVan findForUser(String username) throws Exception {
		return dbConnection.getSalesVanDB().getValueEquals("salesAssociateUsername", username);
	}
	
	public SalesVan getByName(String name) throws IOException {
		return dbConnection.getSalesVanDB().getValue(name);
	}
	
	public void addSalesVan(SalesVan van) throws IOException {
		dbConnection.getSalesVanDB().addValue(van);
	}
	
	public void updateSalesVanName(String username, String salesVanName) throws Exception {
		SalesVan van = new SalesVan(salesVanName, username);
		SalesVan oldVan = findForUser(username);
		dbConnection.getSalesVanDB().remove(oldVan.getPrimaryKey());
		dbConnection.getSalesVanDB().addValue(van);
	}
}