package Main.Models;

public class SalesVanInventoryTableRow {
	
	private String partName;
	private long partNumber;
	private double price;
	private double quantity;
	
	public SalesVanInventoryTableRow() {
		this.partName = "";
		this.price = 0;
		this.quantity = 0;
	}

	public SalesVanInventoryTableRow(String partName, long partNumber, double price, double quantity) {
		this.partName = partName;
		this.partNumber = partNumber;
		this.price = price;
		this.quantity = quantity;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public long getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(long partNumber) {
		this.partNumber = partNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}
