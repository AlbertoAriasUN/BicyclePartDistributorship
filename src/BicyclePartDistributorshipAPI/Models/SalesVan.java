package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModel;

public class SalesVan implements IDatabaseModel {
	private String name;
	private String salesAssociateUsername;
	
	public SalesVan() {
		this.name = "";
		this.salesAssociateUsername = "";
	}

	public SalesVan(String name, String salesAssociateUsername) {
		this.name = name;
		this.salesAssociateUsername = salesAssociateUsername;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalesAssociateUsername() {
		return salesAssociateUsername;
	}

	public void setSalesAssociateUsername(String salesAssociateUsername) {
		this.salesAssociateUsername = salesAssociateUsername;
	}

	@Override
	public Object getPrimaryKey() {
		return name;
	}
	
	@Override
	public String toString() {
		return name + "," + salesAssociateUsername;
	}
}
