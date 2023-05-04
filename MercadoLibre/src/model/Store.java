package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Store {

    public ArrayList<Product> products;
    public ArrayList<Delivery> deliveries;

    public void addProducts(String nombre, String descripcion, double precio, int cantidadDisponible, int categorias,int timesBought){
        products.add(new Product(nombre,descripcion,precio,cantidadDisponible,categorias,timesBought));
    }

    public boolean deleteProduct(String name){
       boolean wasErased = false;

       int i = 0;
       products.remove(i);

       if (products.get(i) == null){
           wasErased = true;
       }

        return wasErased;

    }

    public void addDelivery(String buyerName, double totalPrice, Calendar buyDate){



    }

    public void addProductToDelivery(){

    }

    public int searchProduct(){


        return 0;
    }

    public Store() {
        products = new ArrayList<>();
        deliveries = new ArrayList<>();
    }
}
