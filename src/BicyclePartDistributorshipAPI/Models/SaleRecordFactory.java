package BicyclePartDistributorshipAPI.Models;

import java.util.Date;
import java.util.UUID;

import Database.IDatabaseModelFactory;

public class SaleRecordFactory implements IDatabaseModelFactory {

	@Override
	public Object create(String csv) {
		String[] values = csv.split(",");
		SaleRecord sale = new SaleRecord();
		sale.setDatetime(new Date(Long.parseLong(values[0])));
		sale.setSalesAssociate(values[1]);
		sale.setPartNumber(Long.parseLong(values[2]));
		sale.setQuantity(Integer.parseInt(values[3]));
		if(values.length > 4) {
			sale.setGuid(values[4]);
		}
		else if(values.length == 4) {
			UUID uuid = UUID.randomUUID();
			sale.setGuid(uuid.toString());
		}
		return sale;
	}

}
