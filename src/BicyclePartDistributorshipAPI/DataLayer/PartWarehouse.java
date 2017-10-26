package BicyclePartDistributorshipAPI.DataLayer;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import BicyclePartDistributorshipAPI.Models.BicyclePartListing;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Data Access Object: Maps DB file to Java objects, writes Data back to file
 * @author MAneiro
 */
public class PartWarehouse {
    
    /**
     * HashMap of Part listings with part number as key
     */
    private Map<Long, BicyclePartListing> parts;
    
    /**
     * Path to DB file
     */
    private final String dbFilename;
    
    /**
     * Reads DB file to HashMap
     * @return HashMap of part listings in DB file
     * @throws IOException Exception in reading file
     */
    private Map<Long, BicyclePartListing> readPartsDB() throws IOException {
        Map<Long, BicyclePartListing> parts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFilename))) {
            String line;
            while((line = reader.readLine()) != null){
                BicyclePartListing part = new BicyclePartListing(line);
                parts.put(part.getPartNumber(), part);
            }
            return parts;
        }
        catch(FileNotFoundException e) {
            return parts;
        } 
    }
    
    /**
     * Reads different DB file to HashMap
     * @param dbFilename File to read
     * @return HashMap of part listings in DB file
     * @throws IOException Exception in reading file
     */
    private Map<Long, BicyclePartListing> readPartsDB(String dbFilename) throws IOException {
        Map<Long, BicyclePartListing> parts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFilename))) {
            String line;
            while((line = reader.readLine()) != null){
                BicyclePartListing part = new BicyclePartListing(line);
                parts.put(part.getPartNumber(), part);
            }
        }
        return parts;
    }
    
    /**
     * Writes entire parts HashMap to DB file
     * @throws IOException 
     */
    private void writeAll() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dbFilename))) {
            ArrayList<String> serializedParts = new ArrayList<>();
            parts.forEach((k,v) -> serializedParts.add(v.toString()));
            for(String s : serializedParts){
                writer.write(s + "\n");
            }
        }
    }
    
    /**
     * Constructs empty PartWarehouse
     */
    public PartWarehouse() {
        this.dbFilename = null;
        parts = new HashMap<>();
    }
    
    /**
     * Constructs PartWarehouse
     * @param dbFilename Path to DB file
     * @throws IOException 
     */
    public PartWarehouse(String dbFilename) throws IOException {
        this.dbFilename = dbFilename;
        parts = readPartsDB();
    }
    
    /**
     * Adds data from new DB file
     * @param dbFilename New DB file
     * @throws IOException Exception in reading file 
     */
    public void append(String dbFilename) throws IOException {
        ArrayList<BicyclePartListing> partsList = new ArrayList<>();
        Map<Long,BicyclePartListing> parts = readPartsDB(dbFilename);
        
        parts.forEach((v,k) -> partsList.add(k));
        for(BicyclePartListing part : partsList) {
            if(this.parts.containsKey(part.getPartNumber())) {
                BicyclePartListing existingPart = this.parts.get(part.getPartNumber());
                part.setQuantity(part.getQuantity() + existingPart.getQuantity());
            }
            this.parts.put(part.getPartNumber(), part);
        }
        
        writeAll();
    }
    
    /**
     * Sets new value for Part listing
     * @param partListing New value for part listing
     * @throws IOException Exception in writing to DB file
     */
    public void setPart(BicyclePartListing partListing) throws IOException {
        parts.put(partListing.getPartNumber(), partListing);
        writeAll();
    }
    
    /**
     * Synonym for setPart
     * @param partListing Part listing to add
     * @throws IOException Error in writing to DB file
     */
    public void addPart(BicyclePartListing partListing) throws IOException {
        setPart(partListing);
    }
    
    /**
     * Gets all part listings
     * @return HashMap of all part listings
     * @throws IOException 
     */
    public Map<Long, BicyclePartListing> getParts() throws IOException {
        parts = readPartsDB();
        return parts;
    }
    
    /**
     * Gets a specific part listing
     * @param partName Part name of part listing to get
     * @return Corresponding part listing
     */
    public BicyclePartListing getPart(String partName) throws IOException {
        ArrayList<BicyclePartListing> parts = new ArrayList<>();
        this.parts = readPartsDB();
        this.parts.forEach((v,k) -> parts.add(new BicyclePartListing(k.toString())));
        for(BicyclePartListing part : parts) {
            if(part.getPartName().equals(partName)) {
                return part;
            }
        }
        return null;
    }
    
    /**
     * Gets a specific part listing
     * @param partNumber Part number of part listing to get
     * @return Corresponding part listing
     */
    public BicyclePartListing getPart(Long partNumber) throws IOException {
        parts = readPartsDB();
        return parts.get(partNumber);
    }
    
    /**
     * Gets filename of PartWarehouses's DB
     * @return filename of DB
     */
    public String getDBFilename() {
        return dbFilename;
    }
}
