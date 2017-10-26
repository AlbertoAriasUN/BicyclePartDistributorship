package Main.Models;

/**
 * Defines a row for table in the "Sell Part" tab
 * @author MAneiro
 */
public class PartSaleTableRow {
    
    /**
     * Name of warehouse part was sold in
     */
    private final String warehouseName;
    
    /**
     * Name of part
     */
    private final String partName;
    
    /**
     * Cost of part
     */
    private final double partCost;
    
    /**
     * Timestamp of when part was sold
     * Format "DD/MM/YYYY HH:MM:SS
     */
    private final String timestamp;
    
    /**
     * Constructor for table row
     * @param warehouseName Name of warehouse
     * @param partName Name of part
     * @param partCost Cost of part
     * @param timestamp Timestamp of when part was sold
     */
    public PartSaleTableRow(String warehouseName, String partName, double partCost, String timestamp) {
        this.warehouseName = warehouseName;
        this.partName = partName;
        this.partCost = partCost;
        this.timestamp = timestamp;
    }
    
    /**
     * Gets name of warehouse
     * @return name of warehouse
     */
    public String getWarehouseName() {
        return warehouseName;
    }
    
    /**
     * Gets name of part
     * @return name of part
     */
    public String getPartName() {
        return partName;
    }
    
    /**
     * Gets cost of part
     * @return cost of part
     */
    public double getPartCost() {
        return partCost;
    }
    
    /**
     * Gets timestamp of when part was sold
     * @return timestamp of when part was sold
     */
    public String getTimestamp() {
        return timestamp;
    }
}
