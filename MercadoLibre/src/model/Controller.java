package model;

public class Controller {

    public void agregarProducto(String nombre, String descripcion, double precio, int cantidadDisponible, int categoria) {
        Producto nuevoProducto = new Producto(nombre, descripcion, precio, cantidadDisponible, categoria);
    }
    public void agregarVendedor(String username, String password, String name){
        Vendedor vendedor1 = new Vendedor("usuario1", "contraseña1", "Nombre del vendedor");
    }
    public boolean agregarComprador(String username, String password, String name){
        Comprador comprador = new Comprador(username, password, name);
        return false;
    }
    public  int binarySearch(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}
