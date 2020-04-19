/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author TruongTN
 */
public class SupplierDTO {
    public String supCode;
    public String supName;
    public String address;
    public boolean bit;

    public SupplierDTO(String supCode, String supName, String address, boolean bit) {
        this.supCode = supCode;
        this.supName = supName;
        this.address = address;
        this.bit = bit;
    }

    public SupplierDTO(String supCode, String supName) {
        this.supCode = supCode;
        this.supName = supName;
    }

    
    public SupplierDTO() {
    }

    public String getSupCode() {
        return supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isBit() {
        return bit;
    }

    public void setBit(boolean bit) {
        this.bit = bit;
    }
    
}
