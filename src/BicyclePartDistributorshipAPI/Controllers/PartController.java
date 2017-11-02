package BicyclePartDistributorshipAPI.Controllers;

import Database.Database;
import Tools.BicyclePartTuple;
import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import BicyclePartDistributorshipAPI.Models.BicyclePartFactory;
import BicyclePartDistributorshipAPI.Models.Inventory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for part listings
 * @author MAneiro
 */
public class PartController {

	private DatabaseConnection dbConnection;

	private String warehouseName;
	
    /**
     * Initialize PartController
     * @param warehouse PartWarehouse to be used
     */
    public PartController(String warehouseName) {
    	dbConnection = new DatabaseConnection();
    	this.warehouseName = warehouseName;
    }

    /**
     * Sell a part
     * @param partNumber Part number of part listing to be sold
     * @throws IOException Exception in writing to DB file
     */
    public void sellPart(Long partNumber) throws IOException {
        Inventory inventory = dbConnection.getWarehouseDB(warehouseName).getValue(partNumber);
        if(inventory != null) {
            inventory.setQuantity(inventory.getQuantity() - 1);
            dbConnection.getWarehouseDB(warehouseName).setValue(inventory);
        }
    }

    /**
     * Adds a part
     * @param partListing Part listing to be added
     * @throws IOException Exception in writing to DB file
     */
    public void addPart(BicyclePart part) throws IOException {
        dbConnection.getBicyclePartsDB().addValue(part);
    }

    /**
     * Add part listings from a file
     * @param dbFilename File to be added from
     * @throws IOException Exception in reading file
     */
    public void addPartsFromFile(String dbFilename) throws IOException {
    	Database<BicyclePart> tmp = new Database<>(dbFilename, new BicyclePartFactory());
    	ArrayList<BicyclePart> parts = tmp.getValuesList();

    	for(BicyclePart part : parts) {
    		dbConnection.getBicyclePartsDB().addValue(part);
    	}
    }

    /**
     * Gets a part
     * @param partName Part name of part listing to be retrieved
     * @return Corresponding part listing
     * @throws IOException Exception in reading file
     */
    public BicyclePartTuple getPart(String partName) throws Exception {
    	Database<BicyclePart> bicyclePartsDB = dbConnection.getBicyclePartsDB();
    	Database<Inventory> warehouseDB = dbConnection.getWarehouseDB(warehouseName);
    	
        BicyclePart part = bicyclePartsDB.getValueEquals("partName", partName);
        Integer quantity = warehouseDB.getValue(part.getPrimaryKey()).getQuantity();
        return new BicyclePartTuple(part, quantity);
    }

    /**
     * Gets a part
     * @param partNumber Part number of part listing to be retrieved
     * @return Corresponding part listing
     * @throws IOException Exception in reading file
     */
    public BicyclePartTuple getPart(Long partNumber) throws IOException {
    	Database<BicyclePart> bicyclePartsDB = dbConnection.getBicyclePartsDB();
    	Database<Inventory> warehouseDB = dbConnection.getWarehouseDB(warehouseName);
    	
        Integer quantity = warehouseDB.getValue(partNumber).getQuantity();
        BicyclePart part = bicyclePartsDB.getValue(partNumber);
        return new BicyclePartTuple(part, quantity);
    }

    /**
     * Gets all part listings
     * @return ArrayList of part listings
     * @throws IOException Exception in reading from DB file
     */
    public ArrayList<BicyclePartTuple> getParts() throws IOException {
    	Database<Inventory> warehouseDB = dbConnection.getWarehouseDB(warehouseName);
    	Database<BicyclePart> bicyclePartsDB = dbConnection.getBicyclePartsDB();
        
    	ArrayList<BicyclePartTuple> bicyclePartTuples = new ArrayList<>();
        for(Object key : warehouseDB.getValues().keySet()) {
        	BicyclePart part = bicyclePartsDB.getValue(key);
        	Integer quantity = warehouseDB.getValue(key).getQuantity();
        	bicyclePartTuples.add(new BicyclePartTuple(part, quantity));
        }
        return bicyclePartTuples;
    }
}
