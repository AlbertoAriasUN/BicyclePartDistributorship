package Main;

import BicyclePartDistributorshipAPI.Controllers.PartController;
import BicyclePartDistributorshipAPI.Controllers.WarehouseController;
import BicyclePartDistributorshipAPI.DataLayer.PartWarehouse;
import java.io.IOException;

/**
 * Allows FXML controllers to call API
 * @author Matthew
 */
public class APICaller {
	
    /**
     * Filename of main warehouse DB file
     */
    private final String DB_FILENAME = "warehousedb.txt";
    
    /**
     * Name for main warehouse
     */
    public  static final String MAIN_WAREHOUSE_NAME = "mainwh";
    
    /**
     * Controller containing all warehouses
     */
    private WarehouseController warehouseController;
    
    /**
     * Singleton object used to access API controllers
     */
    private static final APICaller API_CALLER = new APICaller();
    
    /**
     * Constructor for singleton object
     */
    private APICaller() {
        try {
            warehouseController = new WarehouseController();
            warehouseController.addWarehouse(MAIN_WAREHOUSE_NAME, new PartWarehouse(DB_FILENAME));
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }   
    }
    
    /**
     * Access API Controller for all warehouses
     * @return Controller for aggregate warehouse
     * @throws IOException Exception in reading file
     */
    public static PartController getAPIController() throws IOException {
        return API_CALLER.warehouseController.getPartController();
    }
    
    /**
     * Access API Controller for a specific warehouse
     * @param warehouseName
     * @return Controller for specified warehouse
     * @throws IOException 
     */
    public static PartController getAPIController(String warehouseName) throws IOException {
        return API_CALLER.warehouseController.getPartController(warehouseName);
    }
    
    /**
     * Access API Controller to execute warehouse-level operations
     * @return Controller containing all warehouses
     * @throws IOException Exception in reading file
     */
    public static WarehouseController getWarehouseController() throws IOException {
        return API_CALLER.warehouseController;
    }
}
