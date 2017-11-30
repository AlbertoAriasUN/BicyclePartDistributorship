/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModel;

/**
 *
 * @author colinvitkus
 */
public class InvoiceSaleRecord implements IDatabaseModel{
	
	private String saleId;
	
	public InvoiceSaleRecord(String saleId) {
		this.saleId = saleId;
	}
	
	public String getSaleId() {
		return saleId;
	}
	
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	@Override
	public String toString() {
		return saleId;
	}
	
	@Override
	public Object getPrimaryKey() {
		return saleId;
	}
}
