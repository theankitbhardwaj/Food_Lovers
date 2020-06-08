package com.bhardwaj.foodlovers.Models;

public class ModelCart {
    String fName;
    String fDesc;
    int fCount = 0;
    String fPrice;
    String fImage;

    public ModelCart(String fName, String fDesc, int fCount, String fPrice, String fImage) {
        this.fName = fName;
        this.fDesc = fDesc;
        this.fPrice = fPrice;
        this.fImage = fImage;
        this.fCount = fCount;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfDesc() {
        return fDesc;
    }

    public void setfDesc(String fDesc) {
        this.fDesc = fDesc;
    }

    public int getfCount() {
        return fCount;
    }

    public void setfCount(int fCount) {
        this.fCount = fCount;
    }

    public String getfPrice() {
        return fPrice;
    }

    public void setfPrice(String fPrice) {
        this.fPrice = fPrice;
    }

    public String getfImage() {
        return fImage;
    }

    public void setfImage(String fImage) {
        this.fImage = fImage;
    }
}
