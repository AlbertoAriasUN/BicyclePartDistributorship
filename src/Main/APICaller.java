package Main;

import BicyclePartDistributorshipAPI.Controllers.PartController;
import BicyclePartDistributorshipAPI.Controllers.UserController;
import BicyclePartDistributorshipAPI.Controllers.WarehouseController;
import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Allows FXML controllers to call API
 * @author Matthew
 */
public class APICaller {

    /**
     * Access API Controller for a specific warehouse
     * @return Controller for specified warehouse
     * @throws IOException
     */
    public static PartController getPartController() throws IOException {
    	return (new PartController());
    }

    /**
     * Access API Controller to execute warehouse-level operations
     * @param name
     * @return Controller containing all warehouses
     * @throws IOException Exception in reading file
     */
    public static WarehouseController getWarehouseController(String name) throws IOException {
        return (new WarehouseController(name));
    }
    
    public static WarehouseController getMainWarehouseController() throws IOException {
        return (new WarehouseController(WarehouseController.MAIN_WAREHOUSE_NAME));
    }
    
    public static ArrayList<WarehouseController> getWarehouseControllerList() throws Exception {
        ArrayList<WarehouseController> controllers = new ArrayList<>();
        ArrayList<String> warehouseNames = new ArrayList<>();
        
        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.getWarehouseMap().forEach((k,v) -> warehouseNames.add(k));
        for(String name : warehouseNames) {
            controllers.add(new WarehouseController(name));
        }
        
        return controllers;
    }

    public static UserController getUserController() throws IOException {
    	return (new UserController());
    }
}
