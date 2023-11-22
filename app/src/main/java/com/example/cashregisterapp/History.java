package com.example.cashregisterapp;

import java.io.Serializable;
import java.util.Date;

public class History implements Serializable {

    String historyName;
    double price;
    int quantity;

    Date date;

    public History(String historyName, double price, int quantity, Date date) {
        this.historyName = historyName;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    public String getHistoryName() {
        return historyName;
    }

    public void setHistoryName(String historyName) {
        this.historyName = historyName;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "History: " +
                "date=" + date +
                " historyName='" + historyName  +
                " price=" + price +
                " quantity=" + quantity;
    }
}
