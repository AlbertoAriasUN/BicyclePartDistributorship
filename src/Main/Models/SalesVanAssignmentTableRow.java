package Main.Models;

public class SalesVanAssignmentTableRow {
	private String salesAssociate;
	private String salesVan;
	
	public SalesVanAssignmentTableRow() {
		this.salesAssociate = "";
		this.salesVan = "";
	}

	public SalesVanAssignmentTableRow(String salesAssociate, String salesVan) {
		this.salesAssociate = salesAssociate;
		this.salesVan = salesVan;
	}

	public String getSalesAssociate() {
		return salesAssociate;
	}

	public void setSalesAssociate(String salesAssociate) {
		this.salesAssociate = salesAssociate;
	}

	public String getSalesVan() {
		return salesVan;
	}

	public void setSalesVan(String salesVan) {
		this.salesVan = salesVan;
	}
	
	
}
