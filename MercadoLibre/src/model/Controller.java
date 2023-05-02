package model;

import java.util.ArrayList;

public class Controller {

    public ArrayList<Product> products;
    public ArrayList<Delivery> deliveries;

    public void addProducts(String nombre, String descripcion, double precio, int cantidadDisponible, int categorias){
        products.add(new Product(nombre,descripcion,precio,cantidadDisponible,categorias));
    }
    
}
