package BicyclePartDistributorshipAPI.Controllers;

import Database.Database;
import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;
import BicyclePartDistributorshipAPI.Models.BicyclePart;
import BicyclePartDistributorshipAPI.Models.Inventory;
import Tools.BicyclePartTuple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for warehouses
 * @author MAneiro
 */
public class WarehouseController {

    public static final String MAIN_WAREHOUSE_NAME = "Data/Warehouses/mainwh.txt";

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
            int quantity = inventory.get(part.getPartNumber()).getQuantity();
            tuples.add(new BicyclePartTuple(part, quantity));
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
        inventory.setQuantity(inventory.getQuantity() + amount);
        dbConnection.getWarehouseDB(warehouseName).setValue(inventory);
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
