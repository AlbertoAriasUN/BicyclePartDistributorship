package BicyclePartDistributorshipAPI.Controllers;

import BicyclePartDistributorshipAPI.DataLayer.PartWarehouse;
import BicyclePartDistributorshipAPI.Models.BicyclePartListing;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Controller for warehouses
 * @author MAneiro
 */
public class WarehouseController {
    /**
     * Hash table of warehouses
     */
    private HashMap<String, PartWarehouse> warehouses;
    
    /**
     * File containing locations of DB files for all existing warehouses
     */
    private final String WAREHOUSEDB_FILENAME = "warehouses.txt";
    
    /**
     * Constructs Warehouse using 'warehouses' file
     */
    public WarehouseController() {
        BufferedReader reader = null;
        warehouses = new HashMap<>();
        try {
            reader = new BufferedReader(new FileReader(WAREHOUSEDB_FILENAME));
            String line;
            while((line = reader.readLine()) != null) {
                warehouses.put(line.split("\\.")[0], new PartWarehouse(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            warehouses = new HashMap<>();
        }
    }
    
    /**
     * Adds a warehouse
     * @param name name of warehouse to be added
     * @param warehouse warehouse to be added
     * @throws IOException Exception in writing file
     */
    public void addWarehouse(String name, PartWarehouse warehouse) throws IOException {
        warehouses.put(name, warehouse);
        writeAll();
    }
    
    /**
     * Removes a warehouse
     * @param name warehouse to be removed
     * @throws IOException Exception in writing file
     */
    public void removeWarehouse(String name) throws IOException {
        warehouses.remove(name);
        writeAll();
    }
    
    /**
     * Gets HashMap<> of warehouses
     * @return 
     */
    public HashMap<String, PartWarehouse> getWarehouseMap() {
        return warehouses;
    }
    
    /**
     * Gets controller for a specified warehouse
     * @param warehouseName name of warehouse
     * @return controller for warehouse
     */
    public PartController getPartController(String warehouseName) {
        final PartController controller = new PartController(warehouses.get(warehouseName));
        return controller;
    }
    
    /**
     * Creates a new warehouse of aggregate warehouses and returns a controller
     * @return controller for aggregate warehouse
     * @throws IOException Exception in reading file(s)
     */
    public PartController getPartController() throws IOException {
        final String GLOBAL_DB_FILENAME = "globalDB.txt";
        final Collection<PartWarehouse> warehouseList = warehouses.values();
        
        (new FileWriter(GLOBAL_DB_FILENAME)).close();
        PartController global = new PartController(new PartWarehouse(GLOBAL_DB_FILENAME));
        
        for(PartWarehouse warehouse : warehouseList) {
            global.addPartsFromFile(warehouse.getDBFilename());
        }
        
        final PartController controller = new PartController(new PartWarehouse(GLOBAL_DB_FILENAME));
        return controller;
    }
    
    /**
     * Uses a transfer file to transfer parts between warehouses
     * @param filename filename of transfer file
     * @throws IOException Exception in reading file
     */
    public void transferParts(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            PartWarehouse fromWarehouse = warehouses.get(line.split(",")[0]);
            PartWarehouse toWarehouse = warehouses.get(line.split(",")[1]);
            
            while((line = reader.readLine()) != null) {
               String name = line.split(",")[0];
               int quantity = Integer.parseInt(line.split(",")[1]);
               
               BicyclePartListing part = fromWarehouse.getPart(name);
               part.setQuantity(part.getQuantity() - quantity);
               fromWarehouse.setPart(part);
               
               part = toWarehouse.getPart(name);
               if(part != null) {
                   part.setQuantity(part.getQuantity() + quantity);
                   toWarehouse.setPart(part);
               }
               else {
                   part = fromWarehouse.getPart(name);
                   part.setQuantity(quantity);
                   toWarehouse.addPart(part);
               }
            }
        }
    }
    
    /**
     * Write all warehouses to 'warehouse' file
     * @throws IOException Exception in writing file
     */
    private void writeAll() throws IOException {
        ArrayList<String> keys = new ArrayList(warehouses.keySet());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(WAREHOUSEDB_FILENAME))) {
            for(String key : keys) {
                writer.write(key + ".txt\n");
            }
        }
    }
}
