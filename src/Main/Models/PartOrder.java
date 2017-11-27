package Main.Models;

/**
 *
 * @author Matthew
 */
public class PartOrder {

    private long partNumber;

    private String partName;

    private int currentQuantity;

    private int requestedQuantity;

    private int stockThreshold;



	public PartOrder(long partNumber, String partName, int currentQuantity, int requestedQuantity, int stockThreshold) {
		this.partNumber = partNumber;
		this.partName = partName;
		this.currentQuantity = currentQuantity;
		this.requestedQuantity = requestedQuantity;
		this.stockThreshold = stockThreshold;
	}

	/**
	 * @return the partNumber
	 */
	public long getPartNumber() {
		return partNumber;
	}

	/**
	 * @return the partName
	 */
	public String getPartName() {
		return partName;
	}

	/**
	 * @return the currentQuantity
	 */
	public int getCurrentQuantity() {
		return currentQuantity;
	}

	/**
	 * @return the requestedQuantity
	 */
	public int getRequestedQuantity() {
		return requestedQuantity;
	}

	/**
	 * @return the stockThreshold
	 */
	public int getStockThreshold() {
		return stockThreshold;
	}

	/**
	 * @param partNumber the partNumber to set
	 */
	public void setPartNumber(long partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 * @param partName the partName to set
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}

	/**
	 * @param currentQuantity the currentQuantity to set
	 */
	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	/**
	 * @param requestedQuantity the requestedQuantity to set
	 */
	public void setRequestedQuantity(int requestedQuantity) {
		this.requestedQuantity = requestedQuantity;
	}

	/**
	 * @param stockThreshold the stockThreshold to set
	 */
	public void setStockThreshold(int stockThreshold) {
		this.stockThreshold = stockThreshold;
	}


}
