package model;

import exceptions.*;
import model.*;
public class Product implements Comparable<Product>{

        private String name;
        private String description;
        private double price;
        private int inventory;
        private Category category;
        private int timesBought;

    public Product(String nombre, String descripcion, double precio, int cantidadDisponible, int categorias,int timesBought) {
        this.name = nombre;
        this.description = descripcion;
        this.price = precio;
        this.inventory = cantidadDisponible;

        switch (categorias){

            case 1: category = Category.LIBROS;
                break;
            case 2: category = Category.ELECTRONICA;
                break;
            case 3: category = Category.ROPA_Y_ACCESORIOS;
                break;
            case 4: category = Category.ALIMENTOS_Y_BEBIDAS;
                break;
            case 5: category = Category.PAPELERIA;
                break;
            case 6: category = Category.DEPORTES;
                break;
            case 7: category = Category.BELLEZA_Y_CUIDADO_PERSONAL;
                break;
            case 8: category = Category.JUGUETES_Y_JUEGOS;
                break;
        }

        this.timesBought = timesBought;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) throws NotEnoughProductsException{

        if(this.inventory < Math.abs(inventory) ){
            throw new NotEnoughProductsException("No se puede rertirar esa cantidad de objetos de la tienda");
        }

        this.inventory += inventory;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getTimesBought() {
        return timesBought;
    }

    public void setTimesBought(int timesBought) {
        this.timesBought = timesBought;
    }

    @Override
    public int compareTo(Product o) {

        int value = this.name.compareTo(o.getName());

        if(value == 0){
            value = (int)(this.price - o.getPrice());
            if (value == 0){
                value = this.inventory - o.getInventory();
                if (value == 0){
                    value = this.timesBought - o.getTimesBought();
                }
            }
        }

        return value;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", category=" + category +
                ", timesBought=" + timesBought +
                "'}\n'";
    }
}


