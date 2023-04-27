package model;

public class Product {

        private String name;
        private String description;
        private double price;
        private int inventory;
        private Category category;
        private int timesBuyed;

    public Product(String nombre, String descripcion, double precio, int cantidadDisponible, int categorias) {
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

    public void setInventory(int inventory) {
        this.inventory = inventory;
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

    public int getTimesBuyed() {
        return timesBuyed;
    }

    public void setTimesBuyed(int timesBuyed) {
        this.timesBuyed = timesBuyed;
    }
}


