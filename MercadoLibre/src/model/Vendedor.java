package model;


public class Vendedor extends Usuario{

    private Producto Producto;

    public Vendedor(String username, String password, String name) {
        super(username, password, name);
    }
    public void registarProducto(String nombre, String descripcion, double precio, int cantidadDisponible, int categorias){
        Producto productonew = new Producto(nombre,  descripcion, precio,  cantidadDisponible,  categorias);
    }
}
