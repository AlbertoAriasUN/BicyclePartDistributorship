package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModelFactory;

public class SalesVanFactory implements IDatabaseModelFactory {

	@Override
	public Object create(String csv) {
		String[] values = csv.split(",");
		SalesVan van = new SalesVan();
		van.setName(values[0]);
		van.setSalesAssociateUsername(values[1]);
		return van;
	}
	
}
