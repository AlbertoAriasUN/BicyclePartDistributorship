package Main.Models;

public class SalesInvoiceTableRow {
	private String partName;
	private double price;
	private int quantity;
	
	public SalesInvoiceTableRow() {
		this.partName = "";
		this.price = 0;
		this.quantity = 0;
	}

	public SalesInvoiceTableRow(String partName, double price, int quantity) {
		this.partName = partName;
		this.price = price;
		this.quantity = quantity;
	}
	
	public SalesInvoiceTableRow(SalesInvoiceTableRow row) {
		this.partName = row.partName;
		this.price = row.price;
		this.quantity = row.quantity;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.getClass() == o.getClass()) {
			SalesInvoiceTableRow row = (SalesInvoiceTableRow) o;
			if(row.getPartName().equals(getPartName())) {
				return true;
			}
			else return false;
		}
		else return false;
	}
}
