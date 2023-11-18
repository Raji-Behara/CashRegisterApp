package com.example.cashregisterapp;

import java.io.Serializable;

public class Product  implements Serializable {

    String productName;
    int quantity;
    double price;
    public Product(String productName,double price, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
