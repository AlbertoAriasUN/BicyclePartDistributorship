package BicyclePartDistributorshipAPI.Models;

import java.util.Date;

import Database.IDatabaseModel;

public class SaleRecord implements IDatabaseModel {

	private Date datetime;
	private String salesAssociate;
	private long partNumber;
	private int quantity;
	private String guid;
	
	public SaleRecord() {
	}

	public SaleRecord(Date datetime, String salesAssociate, long partNumber, int quantity, String guid) {
		this.datetime = datetime;
		this.salesAssociate = salesAssociate;
		this.partNumber = partNumber;
		this.quantity = quantity;
		this.guid = guid;
	}

	public Date getDatetime() {
		return datetime;
	}


	public String getSalesAssociate() {
		return salesAssociate;
	}


	public long getPartNumber() {
		return partNumber;
	}


	public int getQuantity() {
		return quantity;
	}


	public String getGuid() {
		return guid;
	}


	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}


	public void setSalesAssociate(String salesAssociate) {
		this.salesAssociate = salesAssociate;
	}


	public void setPartNumber(long partNumber) {
		this.partNumber = partNumber;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public void setGuid(String guid) {
		this.guid = guid;
	}


	@Override
	public String toString() {
		return datetime.getTime() + "," + salesAssociate + "," + partNumber + "," + quantity + "," + guid;
	}
	
	@Override
	public Object getPrimaryKey() {
		return guid;
	}
}
