package BicyclePartDistributorshipAPI.DataLayer;

import Database.Database;
import Database.DatabaseListModel;
import Database.DatabaseListModelFactory;
import BicyclePartDistributorshipAPI.Models.BicyclePartListing;
import BicyclePartDistributorshipAPI.Models.BicyclePartListingFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseConnection {
	private final String WAREHOUSE_LIST_DB_FILENAME = "warehouses.txt";
	private Database<DatabaseListModel> warehouseList;
	private HashMap<String, Database<BicyclePartListing>> warehouses;

	public DatabaseConnection() throws IOException {
		warehouseList = new Database<DatabaseListModel>(WAREHOUSE_LIST_DB_FILENAME, new DatabaseListModelFactory());
		warehouses = new HashMap<>();
		final List<String> WAREHOUSE_DB_FILENAMES = warehouseList.getValuesList()
																 .stream().map(s -> s.getDatabaseFilePath())
																 .collect(Collectors.toList());
		for(String filename : WAREHOUSE_DB_FILENAMES) {
			warehouses.put(filename.split("\\.")[0], new Database<BicyclePartListing>(filename, new BicyclePartListingFactory()));
		}
	}

	public Database<DatabaseListModel> getWarehouseListDB() {
		return warehouseList;
	}

	public HashMap<String, Database<BicyclePartListing>> getWarehouses() {
		return warehouses;
	}

	public Database<BicyclePartListing> getWarehouse(String name) {
		return warehouses.get(name);
	}
}
