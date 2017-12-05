package BicyclePartDistributorshipAPI.Models;

import java.util.Date;

import Database.IDatabaseModelFactory;

public class InvoiceSaleRecordFactory implements IDatabaseModelFactory
{
	@Override
	public Object create(String csv)
	{
		String[] values = csv.split(",");
		InvoiceSaleRecord record = new InvoiceSaleRecord();
		record.setPartNumber(Long.parseLong(values[0]));
		record.setQuantity(Integer.parseInt(values[1]));
		record.setSalesAssociate(values[2]);
		record.setDatetime(new Date(Long.parseLong(values[3])));
		return record;
	}
}
