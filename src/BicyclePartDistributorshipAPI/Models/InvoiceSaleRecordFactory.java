package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModelFactory;

public class InvoiceSaleRecordFactory implements IDatabaseModelFactory
{
	@Override
	public Object create(String csv)
	{
		return (new InvoiceSaleRecord(csv));
	}
}
