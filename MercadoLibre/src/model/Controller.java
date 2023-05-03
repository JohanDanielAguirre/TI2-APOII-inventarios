package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Controller {

    public ArrayList<Product> products;
    public ArrayList<Delivery> deliveries;

    public void addProducts(String nombre, String descripcion, double precio, int cantidadDisponible, int categorias){
        products.add(new Product(nombre,descripcion,precio,cantidadDisponible,categorias));
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

    public Controller() {
        products = new ArrayList<>();
        deliveries = new ArrayList<>();
    }
}
