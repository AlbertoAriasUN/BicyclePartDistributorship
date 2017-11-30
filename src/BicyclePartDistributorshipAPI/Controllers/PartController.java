package BicyclePartDistributorshipAPI.Controllers;

import Database.Database;
import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import BicyclePartDistributorshipAPI.Models.BicyclePartFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Controller for part listings
 * @author MAneiro
 */
public class PartController {

    private final DatabaseConnection dbConnection;
	
    /**
     * Initialize PartController
     */
    public PartController() {
    	dbConnection = new DatabaseConnection();
    }

    /**
     * Adds a part
     * @param part
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
    public BicyclePart getPart(String partName) throws Exception {
    	Map<Object, BicyclePart> parts = dbConnection.getBicyclePartsDB().getValues();
        return parts.get(partName);
    }

    /**
     * Gets a part
     * @param partNumber Part number of part listing to be retrieved
     * @return Corresponding part listing
     * @throws IOException Exception in reading file
     */
    public BicyclePart getPart(Long partNumber) throws IOException {
        return dbConnection.getBicyclePartsDB().getValue(partNumber);
    }

    /**
     * Gets all part listings
     * @return ArrayList of part listings
     * @throws IOException Exception in reading from DB file
     */
    public ArrayList<BicyclePart> getParts() throws IOException {
        return dbConnection.getBicyclePartsDB().getValuesList();
    }
}
