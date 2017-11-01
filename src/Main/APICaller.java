package Main;

import BicyclePartDistributorshipAPI.Controllers.PartController;
import BicyclePartDistributorshipAPI.Controllers.UserController;
import BicyclePartDistributorshipAPI.Controllers.WarehouseController;
import Database.Database;
import BicyclePartDistributorshipAPI.Models.Inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Allows FXML controllers to call API
 * @author Matthew
 */
public class APICaller {

    /**
     * Access API Controller for all warehouses
     * @return Controller for aggregate warehouse
     * @throws IOException Exception in reading file
     */
    public static ArrayList<PartController> getAllPartControllers() throws IOException {
    	ArrayList<PartController> partControllers = new ArrayList<>();
    	HashMap<String, Database<Inventory>> warehouses = new WarehouseController().getWarehouseMap();
    	for(String key : warehouses.keySet()) {
    		partControllers.add(new WarehouseController().getPartController(key));
    	}
    	return partControllers;
    }

    /**
     * Access API Controller for a specific warehouse
     * @param warehouseName
     * @return Controller for specified warehouse
     * @throws IOException
     */
    public static PartController getPartController(String warehouseName) throws IOException {
    	return (new WarehouseController().getPartController(warehouseName));
    }

    /**
     * Access API Controller to execute warehouse-level operations
     * @return Controller containing all warehouses
     * @throws IOException Exception in reading file
     */
    public static WarehouseController getWarehouseController() throws IOException {
        return (new WarehouseController());
    }

    public static UserController getUserController() throws IOException {
    	return (new UserController());
    }
}
