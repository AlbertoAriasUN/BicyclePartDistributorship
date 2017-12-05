/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BicyclePartDistributorshipAPI.Models;

import java.util.Date;

import Database.IDatabaseModel;

/**
 *
 * @author colinvitkus
 */
public class InvoiceSaleRecord implements IDatabaseModel{
	
	private long partNumber;
	private int quantity;
	private String salesAssociate;
	private Date datetime;
	
	public InvoiceSaleRecord() {
		this.partNumber = 0;
		this.quantity = 0;
		this.salesAssociate = "";
		this.datetime = null;
	}
	
	public InvoiceSaleRecord(long partNumber, int quantity, String salesAssociate, Date datetime) {
		this.partNumber = partNumber;
		this.quantity = quantity;
		this.salesAssociate = salesAssociate;
		this.datetime = datetime;
	}

	public long getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(long partNumber) {
		this.partNumber = partNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSalesAssociate() {
		return salesAssociate;
	}

	public void setSalesAssociate(String salesAssociate) {
		this.salesAssociate = salesAssociate;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	@Override
	public Object getPrimaryKey() {
		return partNumber;
	}
	
	@Override
	public String toString() {
		return partNumber + "," + quantity + "," + salesAssociate + "," + datetime.getTime();
	}
	
}
