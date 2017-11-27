package Main.Models;

import BicyclePartDistributorshipAPI.Models.BicyclePart;
import Tools.BicyclePartTuple;

public class ExaminePartTableRow {

	private long partNumber;
	private String partName;
	private double listPrice;
	private double salePrice;
	private boolean isOnSale;
	private int quantity;

	public ExaminePartTableRow(long partNumber, String partName, double listPrice, double salePrice, boolean isOnSale, int quantity) {
		this.partNumber = partNumber;
		this.partName = partName;
		this.listPrice = listPrice;
		this.salePrice = salePrice;
		this.isOnSale = isOnSale;
		this.quantity = quantity;
	}

	public ExaminePartTableRow(BicyclePartTuple tuple) {
		BicyclePart part = tuple.getBicyclePart();
		int quantity = tuple.getQuantity();

		this.partNumber = part.getPartNumber();
		this.partName = part.getPartName();
		this.listPrice = part.getListPrice();
		this.salePrice = part.getSalePrice();
		this.isOnSale = part.isOnSale();
		this.quantity = quantity;
	}

	/**
	 * @return the partNumber
	 */
	public long getPartNumber() {
		return partNumber;
	}
	/**
	 * @return the partName
	 */
	public String getPartName() {
		return partName;
	}
	/**
	 * @return the listPrice
	 */
	public double getListPrice() {
		return listPrice;
	}
	/**
	 * @return the salePrice
	 */
	public double getSalePrice() {
		return salePrice;
	}
	/**
	 * @return the isOnSale
	 */
	public boolean getIsOnSale() {
		return isOnSale;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param partNumber the partNumber to set
	 */
	public void setPartNumber(long partNumber) {
		this.partNumber = partNumber;
	}
	/**
	 * @param partName the partName to set
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}
	/**
	 * @param listPrice the listPrice to set
	 */
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}
	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * @param isOnSale the isOnSale to set
	 */
	public void setIsOnSale(boolean isOnSale) {
		this.isOnSale = isOnSale;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
