package BicyclePartDistributorshipAPI.Controllers;

import Database.Database;
import Database.DatabaseListModel;
import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;
import BicyclePartDistributorshipAPI.Models.BicyclePartListing;

import java.io.IOException;
import java.util.HashMap;

/**
 * Controller for warehouses
 * @author MAneiro
 */
public class WarehouseController {

	public static final String MAIN_WAREHOUSE_NAME = "mainwh.txt";

	private DatabaseConnection dbConnection;

    public WarehouseController() throws IOException {
    	dbConnection = new DatabaseConnection();
    }

    /**
     * Adds a warehouse
     * @param name name of warehouse to be added
     * @param warehouse warehouse to be added
     * @throws IOException Exception in writing file
     */
    public void addWarehouse(String filename) throws IOException {
    	dbConnection.getWarehouseListDB().addValue(new DatabaseListModel(filename));
    }

    /**
     * Removes a warehouse
     * @param name warehouse to be removed
     * @throws IOException Exception in writing file
     */
    public void removeWarehouse(String name) throws IOException {
    	dbConnection.getWarehouseListDB().remove(name);
    }

    /**
     * Gets HashMap<> of warehouses
     * @return
     */
    public HashMap<String, Database<BicyclePartListing>> getWarehouseMap() {
        return dbConnection.getWarehouses();
    }

    /**
     * Gets controller for a specified warehouse
     * @param warehouseName name of warehouse
     * @return controller for warehouse
     */
    public PartController getPartController(String warehouseName) {
        final PartController controller = new PartController(dbConnection.getWarehouse(warehouseName));
        return controller;
    }

    /**
     * Uses a transfer file to transfer parts between warehouses
     * @param filename filename of transfer file
     * @throws IOException Exception in reading file
     */
    /*public void transferParts(String filename) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
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
    }*/
}
