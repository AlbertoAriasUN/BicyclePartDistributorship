package BicyclePartDistributorshipAPI.Controllers;
import BicyclePartDistributorshipAPI.DataLayer.PartWarehouse;
import BicyclePartDistributorshipAPI.Models.BicyclePartListing;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Controller for part listings
 * @author MAneiro
 */
public class PartController {
    
    /**
     * PartWarehouse to be used
     */
    private final PartWarehouse PartWarehouse;
    
    /**
     * Initialize PartController
     * @param partWarehouse PartWarehouse to be used
     */
    public PartController(PartWarehouse partWarehouse){
        this.PartWarehouse = partWarehouse;
    }
    
    /**
     * Sell a part
     * @param partNumber Part number of part listing to be sold
     * @throws IOException Exception in writing to DB file
     */
    public void sellPart(Long partNumber) throws IOException {
        BicyclePartListing part = PartWarehouse.getPart(partNumber);
        if(part != null) {
            part.setQuantity(part.getQuantity() - 1);
            PartWarehouse.setPart(part);
        }
        
    }
    
    /**
     * Adds a part
     * @param partListing Part listing to be added
     * @throws IOException Exception in writing to DB file
     */
    public void addPart(BicyclePartListing partListing) throws IOException {
        PartWarehouse.addPart(partListing);
    }
    
    /**
     * Add part listings from a file
     * @param dbFilename File to be added from
     * @throws IOException Exception in reading file
     */
    public void addPartsFromFile(String dbFilename) throws IOException {
        PartWarehouse.append(dbFilename);
    }
    
    /**
     * Gets a part
     * @param partName Part name of part listing to be retrieved
     * @return Corresponding part listing
     * @throws IOException Exception in reading file
     */
    public BicyclePartListing getPart(String partName) throws IOException {
        return PartWarehouse.getPart(partName);
    }
    
    /**
     * Gets a part
     * @param partNumber Part number of part listing to be retrieved
     * @return Corresponding part listing
     * @throws IOException Exception in reading file
     */
    public BicyclePartListing getPart(Long partNumber) throws IOException {
        return PartWarehouse.getPart(partNumber);
    }
    
    /**
     * Gets all part listings
     * @return ArrayList of part listings
     * @throws IOException Exception in reading from DB file
     */
    public ArrayList<BicyclePartListing> getParts() throws IOException {
        Map<Long, BicyclePartListing> parts = PartWarehouse.getParts();
        ArrayList<BicyclePartListing> _parts = new ArrayList<>();
        parts.forEach((v,k) -> _parts.add(k));
        return _parts;
    }
}
