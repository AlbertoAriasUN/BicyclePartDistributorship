package BicyclePartDistributorshipAPI.Models;

public class BicyclePartListingFactory implements IDBModelFactory {
	@Override
	public Object create(String csv) {
		return new BicyclePartListing(csv);
	}
}
