package BicyclePartDistributorshipAPI.Controllers;

import BicyclePartDistributorshipAPI.DataLayer.DatabaseConnection;

/**
 *
 * @author colinvitkus
 */
public class InvoiceController {
    
    private DatabaseConnection dbConnection;
   
    /**
     *
     */
    public InvoiceController() { 
        dbConnection = new DatabaseConnection();
    }
    
    public void addSale(String name) {
        
    }
}