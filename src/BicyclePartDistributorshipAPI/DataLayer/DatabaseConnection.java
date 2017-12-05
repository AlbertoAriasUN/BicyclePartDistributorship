package BicyclePartDistributorshipAPI.DataLayer;

import Database.Database;
import Database.DatabaseListModel;
import Database.DatabaseListModelFactory;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import BicyclePartDistributorshipAPI.Models.BicyclePartFactory;
import BicyclePartDistributorshipAPI.Models.Inventory;
import BicyclePartDistributorshipAPI.Models.InventoryFactory;
import BicyclePartDistributorshipAPI.Models.InvoiceSaleRecord;
import BicyclePartDistributorshipAPI.Models.InvoiceSaleRecordFactory;
import BicyclePartDistributorshipAPI.Models.SalesVan;
import BicyclePartDistributorshipAPI.Models.SalesVanFactory;
import BicyclePartDistributorshipAPI.Models.User;
import BicyclePartDistributorshipAPI.Models.UserFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseConnection {
	
	private final String WAREHOUSE_LIST_DB_FILENAME = "Data/warehouses.txt";
	private final String INVOICE_LIST_DB_FILENAME = "Data/invoices.txt";
	private final String BICYCLE_PARTS_DB_FILENAME = "Data/bikeParts.txt";
	private final String USER_DB_FILENAME = "Data/users.txt";
	private final String SALES_VAN_DB_FILENAME = "Data/salesVans.txt";

	public Database<DatabaseListModel> getWarehouseListDB() throws IOException {
		return new Database<>(WAREHOUSE_LIST_DB_FILENAME, new DatabaseListModelFactory());
	}
	
	public Database<DatabaseListModel> getInvoiceListDB() throws IOException {
		return new Database<>(INVOICE_LIST_DB_FILENAME, new DatabaseListModelFactory());
	}
	
	public HashMap<String, Database<Inventory>> getWarehouseMap() throws IOException {
		HashMap<String, Database<Inventory>> warehouses = new HashMap<>();
		final List<String> WAREHOUSE_DB_FILENAMES = getWarehouseListDB().getValuesList()
																 		.stream().map(s -> s.getDatabaseFilePath())
																 		.collect(Collectors.toList());
		
		for(String filename : WAREHOUSE_DB_FILENAMES) {
			String[] values = filename.split("/");
			String warehouseName = values[values.length - 1].split("\\.")[0];
			warehouses.put(warehouseName, new Database<>(filename, new InventoryFactory()));
		}
		return warehouses;
	}

	public HashMap<String, Database<InvoiceSaleRecord>> getInvoiceMap() throws IOException {
		HashMap<String, Database<InvoiceSaleRecord>> invoices = new HashMap<>();
		final List<String> INVOICE_DB_FILENAMES = getInvoiceListDB().getValuesList()
																	 .stream().map(s -> s.getDatabaseFilePath())
																	 .collect(Collectors.toList());
		for(String filename : INVOICE_DB_FILENAMES) {
			String[] values = filename.split("/");
			String invoiceName = values[values.length - 1].split("\\.")[0];
			invoices.put(invoiceName, new Database<>(filename, new InvoiceSaleRecordFactory()));
		}
		return invoices;	
	}
	
	public Database<Inventory> getWarehouseDB(String name) throws IOException {
		return getWarehouseMap().get(name);
	}

	public Database<InvoiceSaleRecord> getInvoiceDB(String name) throws IOException {
		return getInvoiceMap().get(name);
	}
	
	public Database<BicyclePart> getBicyclePartsDB() throws IOException {
		return new Database<>(BICYCLE_PARTS_DB_FILENAME, new BicyclePartFactory());
	}

	public Database<User> getUserDB() throws IOException {
		return new Database<>(USER_DB_FILENAME, new UserFactory());
	}

	
	public Database<SalesVan> getSalesVanDB() throws IOException {
		return new Database<>(SALES_VAN_DB_FILENAME, new SalesVanFactory());
	}
}
