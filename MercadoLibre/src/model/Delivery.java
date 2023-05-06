package model;

import exceptions.InvalidDataException;
import exceptions.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
       double acum = 0;

        for (Product product : products) {
            acum += product.getPrice();
        }

        totalPrice = acum;
    }

    public Calendar getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Calendar buyDate) {
        this.buyDate = buyDate;
    }

    public Delivery(String buyerName, Calendar buyDate) {
        this.buyerName = buyerName;
        this.totalPrice = 0;
        this.buyDate = buyDate;
        products = new ArrayList<>();

    }

    private void sort(){
        Collections.sort(products);
    }



    public ArrayList<Product> getProducts() {
        return products;
    }

    public void removeProduct(int i){
        if (i> -1){
            products.remove(i);
            setTotalPrice();
        }

    }

    public void addProducts(Product product) throws InvalidDataException {

        if (product.getPrice() < 0){
            throw new InvalidDataException("No se pueden añadir valores negativos en precio");
        }

        products.add(product);
        setTotalPrice();
        sort();
    }
}
