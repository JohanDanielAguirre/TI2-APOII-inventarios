package model;

public class Producto {

        private String nombre;
        private String descripcion;
        private double precio;
        private int cantidadDisponible;
        private Categoria categoria;
        private int vecesComprado;

    public Producto(String nombre, String descripcion, double precio, int cantidadDisponible, Categoria categoria, int vecesComprado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.categoria = categoria;
        this.vecesComprado = vecesComprado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getVecesComprado() {
        return vecesComprado;
    }

    public void setVecesComprado(int vecesComprado) {
        this.vecesComprado = vecesComprado;
    }
}


