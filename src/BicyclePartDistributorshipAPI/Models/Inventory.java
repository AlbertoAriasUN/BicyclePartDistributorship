package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModel;

public class Inventory implements IDatabaseModel {

	private long bicyclePartNumber;
	private int quantity;

	public Inventory() {
            bicyclePartNumber = 0;
            quantity = 0;
	}

	public Inventory(long bicyclePartNumber, int quantity) {
            this.bicyclePartNumber = bicyclePartNumber;
            this.quantity = quantity;
	}

	public long getBicyclePartNumber() {
            return bicyclePartNumber;
	}

	public int getQuantity() {
            return quantity;
	}

	public void setBicyclePartNumber(long bicyclePartNumber) {
            this.bicyclePartNumber = bicyclePartNumber;
	}

	public void setQuantity(int quantity) {
            this.quantity = quantity;
	}

	@Override
	public String toString() {
            return bicyclePartNumber + "," + quantity;
	}

	@Override
	public Object getPrimaryKey() {
            return bicyclePartNumber;
	}
}
