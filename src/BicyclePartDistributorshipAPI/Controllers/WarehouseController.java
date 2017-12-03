package BicyclePartDistributorshipAPI.Controllers;

import Database.Database;
import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import BicyclePartDistributorshipAPI.Models.Inventory;
import BicyclePartDistributorshipAPI.Models.InventoryFactory;
import Tools.BicyclePartTuple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for warehouses
 * @author MAneiro
 */
public class WarehouseController {

    public static final String MAIN_WAREHOUSE_NAME = "mainwh";

    private final DatabaseConnection dbConnection;
    private final String warehouseName;

    public WarehouseController(String warehouseName) throws IOException {
        dbConnection = new DatabaseConnection();
        this.warehouseName = warehouseName;
    }

    /**
     * Gets HashMap<> of warehouses
     * @return
     * @throws IOException
     */
    public HashMap<String, Database<Inventory>> getWarehouseMap() throws IOException {
        return dbConnection.getWarehouseMap();
    }

    public Map<Object, Inventory> getInventoryMap() throws IOException {
        return dbConnection.getWarehouseDB(warehouseName).getValues();
    }

    public ArrayList<Inventory> getInventoryList() throws IOException {
        ArrayList<Inventory> inventory = new ArrayList<>();
        for(Inventory value : getInventoryMap().values()) {
            inventory.add(value);
        }
        return inventory;
    }

    public ArrayList<BicyclePartTuple> getPartTuples() throws IOException {
        ArrayList<BicyclePartTuple> tuples = new ArrayList<>();
        Map<Object, Inventory> inventory = getInventoryMap();
        ArrayList<BicyclePart> parts = dbConnection.getBicyclePartsDB().getValuesList();

        for(BicyclePart part : parts) {
        	if(inventory.containsKey(part.getPrimaryKey())) {
        		int quantity = inventory.get(part.getPrimaryKey()).getQuantity();
                tuples.add(new BicyclePartTuple(part, quantity));
        	}
        }
        return tuples;
    }

    public BicyclePartTuple getPartTuple(String name) throws Exception {
        Map<Object, Inventory> inventory = getInventoryMap();
        BicyclePart part = dbConnection.getBicyclePartsDB().getValueEquals("partName", name);
        return new BicyclePartTuple(part, inventory.get(part.getPartNumber()).getQuantity());
    }

    public BicyclePartTuple getPartTuple(long number) throws IOException {
        Map<Object, Inventory> inventory = getInventoryMap();
        BicyclePart part = dbConnection.getBicyclePartsDB().getValue(number);
        return new BicyclePartTuple(part, inventory.get(number).getQuantity());
    }

    public void addInventory(long partNumber, int amount) throws IOException {
        Inventory inventory = dbConnection.getWarehouseDB(warehouseName).getValue(partNumber);
        if(inventory == null) {
        	dbConnection.getWarehouseDB(warehouseName).addValue(new Inventory(partNumber, amount));
        }
        else {
        	inventory.setQuantity(inventory.getQuantity() + amount);
        	dbConnection.getWarehouseDB(warehouseName).setValue(inventory);
        }
    }
    
    public void removeInventory(long partNumber, int amount) throws IOException {
        Inventory inventory = dbConnection.getWarehouseDB(warehouseName).getValue(partNumber);
        if(inventory == null) {
        	return;
        }
        else {
        	inventory.setQuantity(inventory.getQuantity() - amount);
        	dbConnection.getWarehouseDB(warehouseName).setValue(inventory);
        }
    }
    
    public void transferPartsTo(String fromWarehouseName, File transferFile) throws Exception {
		InventoryFactory factory = new InventoryFactory();
    	
    	try (BufferedReader reader = new BufferedReader(new FileReader(transferFile))) {
    		String line = reader.readLine();
    		Database<Inventory> fromWarehouse = dbConnection.getWarehouseDB(fromWarehouseName);
    		Database<Inventory> toWarehouse = dbConnection.getWarehouseDB(warehouseName);
    		
    		while((line = reader.readLine()) != null) {
    			Inventory fileInventory = (Inventory) factory.create(line);
    			Inventory fromInventory = fromWarehouse.getValue(fileInventory.getPrimaryKey());
    			Inventory toInventory = toWarehouse.getValue(fileInventory.getPrimaryKey());
    			if(fromInventory != null && fromInventory.getQuantity() >= fileInventory.getQuantity()) {
    				fromInventory.setQuantity(fromInventory.getQuantity() - fileInventory.getQuantity());
    				fromWarehouse.setValue(fromInventory);
    				if(toInventory == null) {
    					toWarehouse.addValue(fileInventory);
    				}
    				else {
    					toInventory.setQuantity(toInventory.getQuantity() + fileInventory.getQuantity());
    					toWarehouse.setValue(toInventory);
    				}
    			}
    		}
    	}
    }
}
