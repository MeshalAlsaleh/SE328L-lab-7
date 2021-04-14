package com.example.se328llab7;

class Record {

    String title;
    String productName;
    String productType;
    int productQuantity;

    static int count;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    Record(String title,String productName, String productType, int productQuantity){
        this.title=title;
        this.productName=productName;
        this.productType=productType;
        this.productQuantity=productQuantity;
    }
    Record(){

    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        count++;
        this.productName = productName;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductQuantity() {
        return this.productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
