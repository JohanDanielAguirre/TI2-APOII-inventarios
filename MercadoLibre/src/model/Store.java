package model;

import exceptions.NotEnoughProductsException;
import exceptions.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.Calendar;

public class Store {

    public ArrayList<Product> products;
    public ArrayList<Delivery> deliveries;

    public void addProducts(String nombre, String descripcion, double precio, int cantidadDisponible, int categorias,int timesBought){

        Product product = new Product(nombre,descripcion,precio,cantidadDisponible,categorias,timesBought);
        int index = 0;
        try {
            index = searchProductSpecific(nombre);
        } catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if (index > -1){
            try {
                products.get(index).setInventory(cantidadDisponible);
            } catch (NotEnoughProductsException e) {
                System.out.println(e.getMessage());
            }
        }else {
            products.add(product);
        }


        bubbleSort();
    }

    public boolean deleteProduct(String name){
       boolean wasErased = false;

        int i = 0;
        try {
            i = searchProductSpecific(name);
        } catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
        }
        products.remove(i);

       if (products.get(i) == null){
           wasErased = true;
       }

        bubbleSort();

        return wasErased;

    }

    public void addDelivery(String buyerName, double totalPrice, Calendar buyDate){



    }

    public void addProductToDelivery(){

    }

    public int searchProductSpecific(String name) throws ObjectNotFoundException {

        int left = 0;

        int right = products.size()-1;

        while(left <= right){

            int mid = (left+right)/2;

            Product midP = products.get(mid);

            if (midP.getName().equals(name)){
                return mid;
            }else if(midP.getName().compareTo(name) < 0){
                right = mid - 1;
            }else if(midP.getName().compareTo(name) > 0) {
                left = mid + 1;
            }

        }

        throw new ObjectNotFoundException("No se encontro el objeto");

    }

    private void bubbleSort() {
        for (int i = 0; i < products.size(); i++) {
            for (int j = 1; j < products.size()-i; j++) {
                if(products.get(j).compareTo(products.get(j-1))<0){
                    // get values to swap
                    Product anterior = products.get(j-1);
                    Product actual = products.get(j);
                    // swap
                    products.set(j,anterior);
                    products.set(j-1,actual);
                }
            }

        }
    }

    public Store() {
        products = new ArrayList<>();
        deliveries = new ArrayList<>();
    }
}
