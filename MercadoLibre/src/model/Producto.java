package model;

public class Producto {

        private String nombre;
        private String descripcion;
        private double precio;
        private int cantidadDisponible;
        private Categoria categoria;
        private int vecesComprado;

    public Producto(String nombre, String descripcion, double precio, int cantidadDisponible, int categorias) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;

        switch (categorias){

            case 1: categoria = Categoria.LIBROS;
                break;
            case 2: categoria = Categoria.ELECTRONICA;
                break;
            case 3: categoria = Categoria.ROPA_Y_ACCESORIOS;
                break;
            case 4: categoria = Categoria.ALIMENTOS_Y_BEBIDAS;
                break;
            case 5: categoria = Categoria.PAPELERIA;
                break;
            case 6: categoria = Categoria.DEPORTES;
                break;
            case 7: categoria = Categoria.BELLEZA_Y_CUIDADO_PERSONAL;
                break;
            case 8: categoria = Categoria.JUGUETES_Y_JUEGOS;
                break;
        }

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

    public int getVecesComprado() {
        return vecesComprado;
    }

    public void setVecesComprado(int vecesComprado) {
        this.vecesComprado = vecesComprado;
    }
}


