package BicyclePartDistributorshipAPI.DataLayer;

import Database.Database;
import Database.DatabaseListModel;
import Database.DatabaseListModelFactory;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import BicyclePartDistributorshipAPI.Models.BicyclePartFactory;
import BicyclePartDistributorshipAPI.Models.Inventory;
import BicyclePartDistributorshipAPI.Models.InventoryFactory;
import BicyclePartDistributorshipAPI.Models.User;
import BicyclePartDistributorshipAPI.Models.UserFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseConnection {
	
	private final String WAREHOUSE_LIST_DB_FILENAME = "Data/warehouses.txt";
	private final String BICYCLE_PARTS_DB_FILENAME = "Data/bikeParts.txt";
	private final String USER_DB_FILENAME = "Data/users.txt";

	public DatabaseConnection() {
	}

	public Database<DatabaseListModel> getWarehouseListDB() throws IOException {
		return new Database<>(WAREHOUSE_LIST_DB_FILENAME, new DatabaseListModelFactory());
	}

	public HashMap<String, Database<Inventory>> getWarehouseMap() throws IOException {
		HashMap<String, Database<Inventory>> warehouses = new HashMap<>();
		final List<String> WAREHOUSE_DB_FILENAMES = getWarehouseListDB().getValuesList()
																 		.stream().map(s -> s.getDatabaseFilePath())
																 		.collect(Collectors.toList());
		for(String filename : WAREHOUSE_DB_FILENAMES) {
			warehouses.put(filename, new Database<>(filename, new InventoryFactory()));
		}
		return warehouses;
	}

	public Database<Inventory> getWarehouseDB(String name) throws IOException {
		return getWarehouseMap().get(name);
	}

	public Database<BicyclePart> getBicyclePartsDB() throws IOException {
		return new Database<>(BICYCLE_PARTS_DB_FILENAME, new BicyclePartFactory());
	}

	public Database<User> getUserDB() throws IOException {
		return new Database<>(USER_DB_FILENAME, new UserFactory());
	}
}
