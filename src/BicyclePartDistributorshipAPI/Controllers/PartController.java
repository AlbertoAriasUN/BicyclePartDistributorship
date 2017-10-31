package BicyclePartDistributorshipAPI.Controllers;

import Database.Database;
import Tools.BicyclePartTuple;
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

    /**
     * PartWarehouse to be used
     */
    private Database<Inventory> partWarehouse;

    /**
     * Bicycle Part definitions
     */
    private Database<BicyclePart> bicycleParts;

    /**
     * Initialize PartController
     * @param warehouse PartWarehouse to be used
     */
    public PartController(Database<Inventory> warehouse, Database<BicyclePart> bicycleParts){
        this.partWarehouse = warehouse;
        this.bicycleParts = bicycleParts;
    }

    /**
     * Sell a part
     * @param partNumber Part number of part listing to be sold
     * @throws IOException Exception in writing to DB file
     */
    public void sellPart(Long partNumber) throws IOException {
        Inventory inventory = partWarehouse.getValue(partNumber);
        if(inventory != null) {
            inventory.setQuantity(inventory.getQuantity() - 1);
            partWarehouse.setValue(inventory);
        }
    }

    /**
     * Adds a part
     * @param partListing Part listing to be added
     * @throws IOException Exception in writing to DB file
     */
    public void addPart(BicyclePart part) throws IOException {
        bicycleParts.addValue(part);
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
    		bicycleParts.addValue(part);
    	}
    }

    /**
     * Gets a part
     * @param partName Part name of part listing to be retrieved
     * @return Corresponding part listing
     * @throws IOException Exception in reading file
     */
    public BicyclePartTuple getPart(String partName) throws Exception {
        BicyclePart part = bicycleParts.getValueEquals("partName", partName);
        Integer quantity = partWarehouse.getValue(part.getPrimaryKey()).getQuantity();
        return new BicyclePartTuple(part, quantity);
    }

    /**
     * Gets a part
     * @param partNumber Part number of part listing to be retrieved
     * @return Corresponding part listing
     * @throws IOException Exception in reading file
     */
    public BicyclePartTuple getPart(Long partNumber) throws IOException {
        Integer quantity = partWarehouse.getValue(partNumber).getQuantity();
        BicyclePart part = bicycleParts.getValue(partNumber);
        return new BicyclePartTuple(part, quantity);
    }

    /**
     * Gets all part listings
     * @return ArrayList of part listings
     * @throws IOException Exception in reading from DB file
     */
    public ArrayList<BicyclePartTuple> getParts() throws IOException {
        ArrayList<BicyclePartTuple> bicyclePartTuples = new ArrayList<>();
        for(Object key : partWarehouse.getValues().keySet()) {
        	BicyclePart part = bicycleParts.getValue(key);
        	Integer quantity = partWarehouse.getValue(key).getQuantity();
        	bicyclePartTuples.add(new BicyclePartTuple(part, quantity));
        }
        return bicyclePartTuples;
    }
}
