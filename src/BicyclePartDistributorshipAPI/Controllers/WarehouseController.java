package BicyclePartDistributorshipAPI.Controllers;

import Database.Database;
import BicyclePartDistributorshipAPI.Models.BicyclePartListing;
import BicyclePartDistributorshipAPI.Models.BicyclePartListingFactory;

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
    private HashMap<String, Database<BicyclePartListing>> warehouses;

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
                warehouses.put(line.split("\\.")[0], new Database<BicyclePartListing>(line, new BicyclePartListingFactory()));
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
    public void addWarehouse(String name, Database<BicyclePartListing> warehouse) throws IOException {
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
    public HashMap<String, Database<BicyclePartListing>> getWarehouseMap() {
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
        final Collection<Database<BicyclePartListing>> warehouseList = warehouses.values();

        (new FileWriter(GLOBAL_DB_FILENAME)).close();
        PartController global = new PartController(new Database<BicyclePartListing>(GLOBAL_DB_FILENAME, new BicyclePartListingFactory()));

        for(Database<BicyclePartListing> warehouse : warehouseList) {
            global.addPartsFromFile(warehouse.getDBFilename());
        }

        final PartController controller = new PartController(new Database<BicyclePartListing>(GLOBAL_DB_FILENAME, new BicyclePartListingFactory()));
        return controller;
    }

    /**
     * Uses a transfer file to transfer parts between warehouses
     * @param filename filename of transfer file
     * @throws IOException Exception in reading file
     */
    public void transferParts(String filename) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            Database<BicyclePartListing> fromWarehouse = warehouses.get(line.split(",")[0]);
            Database<BicyclePartListing> toWarehouse = warehouses.get(line.split(",")[1]);

            while((line = reader.readLine()) != null) {
               String name = line.split(",")[0];
               int quantity = Integer.parseInt(line.split(",")[1]);

               BicyclePartListing part = fromWarehouse.getValueEquals("partName", name);
               part.setQuantity(part.getQuantity() - quantity);
               fromWarehouse.setValue(part);

               part = toWarehouse.getValueEquals("partName", name);
               if(part != null) {
                   part.setQuantity(part.getQuantity() + quantity);
                   toWarehouse.setValue(part);
               }
               else {
                   part = fromWarehouse.getValueEquals("partName", name);
                   part.setQuantity(quantity);
                   toWarehouse.addValue(part);
               }
            }
        }
    }

    /**
     * Write all warehouses to 'warehouse' file
     * @throws IOException Exception in writing file
     */
    private void writeAll() throws IOException {
        ArrayList<String> keys = new ArrayList<>(warehouses.keySet());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(WAREHOUSEDB_FILENAME))) {
            for(String key : keys) {
                writer.write(key + ".txt\n");
            }
        }
    }
}
