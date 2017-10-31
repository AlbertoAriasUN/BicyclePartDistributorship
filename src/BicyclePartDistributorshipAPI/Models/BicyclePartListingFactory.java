package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModelFactory;

public class BicyclePartListingFactory implements IDatabaseModelFactory {
	@Override
	public Object create(String csv) {
		return new BicyclePartListing(csv);
	}
}
