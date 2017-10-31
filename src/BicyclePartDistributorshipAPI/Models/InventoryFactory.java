package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModelFactory;

public class InventoryFactory implements IDatabaseModelFactory {

	@Override
	public Object create(String csv) {
		String[] values = csv.split(",");
		Inventory inventory = new Inventory();
		inventory.setBicyclePartNumber(Long.parseLong(values[0]));
		inventory.setQuantity(Integer.parseInt(values[1]));
		return inventory;
	}

}
