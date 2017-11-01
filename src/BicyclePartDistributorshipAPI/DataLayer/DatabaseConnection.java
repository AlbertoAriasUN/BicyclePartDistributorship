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
	private final String WAREHOUSE_LIST_DB_FILENAME = "Data\\warehouses.txt";
	private Database<DatabaseListModel> warehouseList;
	private HashMap<String, Database<Inventory>> warehouses;

	private final String BICYCLE_PARTS_DB_FILENAME = "Data\\bikeParts.txt";
	private Database<BicyclePart> bicycleParts;

	private final String USER_DB_FILENAME = "Data\\users.txt";
	private Database<User> users;

	public DatabaseConnection() throws IOException {
		warehouseList = new Database<DatabaseListModel>(WAREHOUSE_LIST_DB_FILENAME, new DatabaseListModelFactory());
		warehouses = new HashMap<>();
		final List<String> WAREHOUSE_DB_FILENAMES = warehouseList.getValuesList()
																 .stream().map(s -> s.getDatabaseFilePath())
																 .collect(Collectors.toList());
		for(String filename : WAREHOUSE_DB_FILENAMES) {
			warehouses.put(filename.split("\\.")[0], new Database<Inventory>(filename, new InventoryFactory()));
		}

		bicycleParts = new Database<BicyclePart>(BICYCLE_PARTS_DB_FILENAME, new BicyclePartFactory());
		users = new Database<User>(USER_DB_FILENAME, new UserFactory());
	}

	public Database<DatabaseListModel> getWarehouseListDB() {
		return warehouseList;
	}

	public HashMap<String, Database<Inventory>> getWarehouses() {
		return warehouses;
	}

	public Database<Inventory> getWarehouseDB(String name) {
		return warehouses.get(name);
	}

	public Database<BicyclePart> getBicyclePartsDB() {
		return bicycleParts;
	}

	public Database<User> getUsers() {
		return users;
	}
}
