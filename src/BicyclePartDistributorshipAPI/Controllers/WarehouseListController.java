package BicyclePartDistributorshipAPI.Controllers;

import java.io.IOException;

import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;
import Database.DatabaseListModel;

public class WarehouseListController {
	
	private DatabaseConnection dbConnection;
	
	public WarehouseListController() {
		this.dbConnection = new DatabaseConnection();
	}
	
	public void addWarehouse(String name) throws IOException {
		String filepath = "Data/Warehouses/" + name + ".txt";
		dbConnection.getWarehouseListDB().addValue(new DatabaseListModel(filepath));
	}
	
}
