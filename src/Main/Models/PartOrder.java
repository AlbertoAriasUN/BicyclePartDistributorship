/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public PartOrder(long partNumber, String partName, int currentQuantity, int requestedQuantity) {
        this.partNumber = partNumber;
        this.partName = partName;
        this.currentQuantity = currentQuantity;
        this.requestedQuantity = requestedQuantity;
    }
    
    public long getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(long partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }
}
