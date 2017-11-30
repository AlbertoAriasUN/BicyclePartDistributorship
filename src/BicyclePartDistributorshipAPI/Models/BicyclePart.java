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
     * Minimum quantity of part to keep in stock
     */
    private int stockThreshold;

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
                              double salePrice, boolean isOnSale, int stockThreshold) {
        this.partName = partName;
        this.partNumber = partNumber;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.isOnSale = isOnSale;
        this.stockThreshold = stockThreshold;
    }

	/**
	 * @return Name of part
	 */
	public String getPartName() {
		return partName;
	}

	/**
	 * @return Numeric ID for part
	 */
	public long getPartNumber() {
		return partNumber;
	}

	/**
	 * @return Price of part when not on sale
	 */
	public double getListPrice() {
		return listPrice;
	}

	/**
	 * @return Price of part when on sale
	 */
	public double getSalePrice() {
		return salePrice;
	}

	/**
	 * @return Whether part is on sale
	 */
	public boolean isOnSale() {
		return isOnSale;
	}

	/**
	 * @return Threshold of quantity of part to stock
	 */
	public int getStockThreshold() {
		return stockThreshold;
	}

	/**
	 * @param partName Name of part
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}

	/**
	 * @param partNumber Numeric ID for part
	 */
	public void setPartNumber(long partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 * @param listPrice Price of part when not on sale
	 */
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	/**
	 * @param salePrice Price of part when on sale
	 */
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @param isOnSale Is the part is on sale?
	 */
	public void setOnSale(boolean isOnSale) {
		this.isOnSale = isOnSale;
	}

	/**
	 * @param stockThreshold Threshold of quantity of parts to stock
	 */
	public void setStockThreshold(int stockThreshold) {
		this.stockThreshold = stockThreshold;
	}

	/**
     * Gets price of price checking if it is on sale
     * @return actual price of part
     */
    public double getPrice() {
    	return (isOnSale ? salePrice : listPrice);
    }

	/**
     * Generates a CSV string of the object
     * @return CSV string
     */
    @Override
    public String toString() {
        String output = getPartName() + "," + getPartNumber() + "," +
                        getListPrice() + "," + getSalePrice() + "," +
                        isOnSale() + "," + stockThreshold;
        return output;
    }

	@Override
	public Object getPrimaryKey() {
		return partNumber;
	}
}
