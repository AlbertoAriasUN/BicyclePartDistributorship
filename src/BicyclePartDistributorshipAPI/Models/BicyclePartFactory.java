package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModelFactory;

public class BicyclePartFactory implements IDatabaseModelFactory {
	@Override
	public Object create(String csv) {
		String[] values = csv.split(",");
		BicyclePart bikePart = new BicyclePart();
		bikePart.setPartName(values[0]);
		bikePart.setPartNumber(Long.parseLong(values[1]));
		bikePart.setListPrice(Double.parseDouble(values[2]));
		bikePart.setSalePrice(Double.parseDouble(values[3]));
		bikePart.setIsOnSale(Boolean.parseBoolean(values[4]));
		return bikePart;
	}
}
