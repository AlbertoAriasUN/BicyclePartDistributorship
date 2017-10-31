package Tools;

import BicyclePartDistributorshipAPI.Models.BicyclePart;

public class BicyclePartTuple {
	private BicyclePart bicyclePart;
	private Integer quantity;

	public BicyclePartTuple(BicyclePart bicyclePart, Integer quantity) {
		this.bicyclePart = bicyclePart;
		this.quantity = quantity;
	}

	public BicyclePart getBicyclePart() {
		return bicyclePart;
	}

	public Integer getQuantity() {
		return quantity;
	}
}
