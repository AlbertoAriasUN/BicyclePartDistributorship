package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModel;

/**
 * Model for a listing of a bicycle part in warehouse records
 * @author MAneiro
 */
public class BicyclePart implements IDatabaseModel {

    /**
     * Part Name
     */
    private String partName;

    /**
     * Numeric part ID
     */
    private long partNumber;

    /**
     * Price when not on sale
     */
    private double listPrice;

    /**
     * Price when on sale
     */
    private double salePrice;

    /**
     * Is the part on sale?
     */
    private boolean isOnSale;

    /**
     * Constructs a new listing
     */
    public BicyclePart() {
        this.partName = "";
        this.partNumber = 0;
        this.listPrice = 0.0;
        this.salePrice = 0.0;
        this.isOnSale = false;
    }

    /**
     * Constructs a new listing with given parameters
     * @param partName Name of Part
     * @param partNumber Part ID
     * @param listPrice Listing Price
     * @param salePrice Sale Price
     * @param isOnSale Is the Part on sale?
     * @param quantity Amount of Part in warehouse
     */
    public BicyclePart(String partName, long partNumber, double listPrice,
                              double salePrice, boolean isOnSale) {
        this.partName = partName;
        this.partNumber = partNumber;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.isOnSale = isOnSale;
    }

    /**
     * Gets name of part
     * @return name of part
     */
    public String getPartName() {return partName;}

    /**
     * Gets Part ID
     * @return Part ID
     */
    public long getPartNumber() {return partNumber;}

    /**
     * Gets list price of part
     * @return list price of part
     */
    public double getListPrice() {return listPrice;}

    /**
     * Get sale price of part
     * @return sale price of part
     */
    public double getSalePrice() {return salePrice;}

    /**
     * Gets if part is on sale
     * @return Is part on sale
     */
    public boolean getIsOnSale() {return isOnSale;}

    /**
     * Sets part name
     * @param name Name of part
     */
    public void setPartName(String name) {partName = name;}

    /**
     * Sets part number
     * @param number part number
     */
    public void setPartNumber(long number) {partNumber = number;}

    /**
     * Sets list price
     * @param price list price
     */
    public void setListPrice(double price) {listPrice = price;}

    /**
     * Sets sale price
     * @param price sale price
     */
    public void setSalePrice(double price) {salePrice = price;}

    /**
     * Sets if part is on sale
     * @param isOnSale is part on sale
     */
    public void setIsOnSale(boolean isOnSale) {this.isOnSale = isOnSale;}

    /**
     * Gets price of price checking if it is on sale
     * @return actual price of part
     */
    public double getPrice() {return (isOnSale ? salePrice : listPrice);}

    /**
     * Generates a CSV string of the object
     * @return CSV string
     */
    @Override
    public String toString() {
        String output = getPartName() + "," + getPartNumber() + "," +
                        getListPrice() + "," + getSalePrice() + "," +
                        getIsOnSale();
        return output;
    }

	@Override
	public Object getPrimaryKey() {
		return partNumber;
	}
}
