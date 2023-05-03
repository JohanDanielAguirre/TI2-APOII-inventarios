package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Delivery {

    private String buyerName;
    private ArrayList<Product> products;
    private double totalPrice;
    private Calendar buyDate;

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Calendar getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Calendar buyDate) {
        this.buyDate = buyDate;
    }

    public Delivery(String buyerName, double totalPrice, Calendar buyDate) {
        this.buyerName = buyerName;
        this.totalPrice = totalPrice;
        this.buyDate = buyDate;

        products = new ArrayList<>();
    }
}
