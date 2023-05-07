package model;

import exceptions.*;

import java.util.ArrayList;
import java.time.*;
import java.util.Collections;

public class Delivery implements Comparable<Delivery>{

    private String buyerName;
    private ArrayList<Product> products;
    private double totalPrice;
    private LocalDateTime buyDate;

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

    public LocalDateTime getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDateTime buyDate) {
        this.buyDate = buyDate;
    }

    public Delivery(String buyerName, LocalDateTime buyDate) {
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


    public int searchProduct(String name) throws ObjectNotFoundException {
        int left = 0;
        int right = products.size()-1;
        while(left <= right){
            int mid = (left+right)/2;
            Product midP = products.get(mid);
            if (midP.getName().equals(name)){
                return mid;
            }else if(name.compareTo(midP.getName()) > 0){
                left = mid+1;
            }else if(name.compareTo(midP.getName()) <  0) {
                right = mid-1;
            }
        }
        throw new ObjectNotFoundException("No se encontro el objeto");

    }
    public void removeProduct(String name,int request){
        int i = -1;

        for (int j = 0; j < request; j++) {
            try{
                i = searchProduct(name);
            }catch (ObjectNotFoundException e){
                System.out.println(e.getMessage());
            }

            if (i> -1){
                products.remove(i);
                setTotalPrice();
                sort();
            }
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

    @Override
    public int compareTo(Delivery o) {
        int value = this.buyerName.compareTo(o.getBuyerName());

        if (value == 0){
            value = (int) (this.totalPrice - o.getTotalPrice());
            if (value == 0){
                value = this.buyDate.compareTo(o.getBuyDate());
            }
        }

        return value;
    }
}
