package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModelFactory;

import java.time.LocalDate;

public class InvoiceFactory implements IDatabaseModelFactory
{
	@Override
	public Object create(String csv)
	{
		String[] values = csv.split(",");
		Invoice invoice=new Invoice(null,null,0);
		invoice.setAmount(Integer.parseInt(values[0]));
		invoice.setDay(LocalDate.parse(values[1]));
		invoice.setName(values[2]);
		return invoice;

	}
}
