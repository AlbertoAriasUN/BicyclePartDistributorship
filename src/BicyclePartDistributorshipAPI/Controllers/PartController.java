package BicyclePartDistributorshipAPI.Controllers;

import Database.Database;
import BicyclePartDistributorshipAPI.Models.BicyclePartListing;
import BicyclePartDistributorshipAPI.Models.BicyclePartListingFactory;

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
    private final Database<BicyclePartListing> partWarehouse;

    /**
     * Initialize PartController
     * @param partWarehouse PartWarehouse to be used
     */
    public PartController(Database<BicyclePartListing> partWarehouse){
        this.partWarehouse = partWarehouse;
    }

    /**
     * Sell a part
     * @param partNumber Part number of part listing to be sold
     * @throws IOException Exception in writing to DB file
     */
    public void sellPart(Long partNumber) throws IOException {
        BicyclePartListing part = partWarehouse.getValue(partNumber);
        if(part != null) {
            part.setQuantity(part.getQuantity() - 1);
            partWarehouse.setValue(part);
        }

    }

    /**
     * Adds a part
     * @param partListing Part listing to be added
     * @throws IOException Exception in writing to DB file
     */
    public void addPart(BicyclePartListing partListing) throws IOException {
        partWarehouse.addValue(partListing);
    }

    /**
     * Add part listings from a file
     * @param dbFilename File to be added from
     * @throws IOException Exception in reading file
     */
    public void addPartsFromFile(String dbFilename) throws IOException {
    	Database<BicyclePartListing> tmp = new Database<>(dbFilename, new BicyclePartListingFactory());
    	ArrayList<BicyclePartListing> parts = tmp.getValuesList();

    	for(BicyclePartListing part : parts) {
    		BicyclePartListing existingPart = partWarehouse.getValue(part.getPrimaryKey());
    		if(existingPart != null) {
    			part.setQuantity(part.getQuantity() + existingPart.getQuantity());
    			partWarehouse.setValue(part);
    		}
    		else partWarehouse.addValue(part);
    	}
    }

    /**
     * Gets a part
     * @param partName Part name of part listing to be retrieved
     * @return Corresponding part listing
     * @throws IOException Exception in reading file
     */
    public BicyclePartListing getPart(String partName) throws IOException {
        ArrayList<BicyclePartListing> parts = partWarehouse.getValuesList();
        for(BicyclePartListing part : parts) {
        	if(part.getPartName().equals(partName)) {
        		return part;
        	}
        }
        return null;
    }

    /**
     * Gets a part
     * @param partNumber Part number of part listing to be retrieved
     * @return Corresponding part listing
     * @throws IOException Exception in reading file
     */
    public BicyclePartListing getPart(Long partNumber) throws IOException {
        return partWarehouse.getValue(partNumber);
    }

    /**
     * Gets all part listings
     * @return ArrayList of part listings
     * @throws IOException Exception in reading from DB file
     */
    public ArrayList<BicyclePartListing> getParts() throws IOException {
        return partWarehouse.getValuesList();
    }
}
