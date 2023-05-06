package model;

import exceptions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class Store {

    public ArrayList<Product> products;
    public ArrayList<Delivery> deliveries;

    private ArrayList<Product> aux;

    public void addProducts(String nombre, String descripcion, double precio, int cantidadDisponible, int categorias,int timesBought){

        Product product = new Product(nombre,descripcion,precio,cantidadDisponible,categorias,timesBought);
        int index;

        try {
            index = searchProductSpecific(nombre);
            products.get(index).setInventory(cantidadDisponible);
        } catch (ObjectNotFoundException e) {
            products.add(product);
        } catch (NotEnoughProductsException e) {
            System.out.println(e.getMessage());
        }


        sort();
    }

    public boolean deleteProduct(String name){
       boolean wasErased = false;
        Product toDelete = null;
        int i = 0;
        try {
            i = searchProductSpecific(name);
            toDelete = products.get(i);
        } catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
        }

        products.remove(i);


       if (toDelete != null && !products.contains(toDelete)){
           wasErased = true;
       }

        sort();

        return wasErased;

    }

    public void createDelivery(String buyerName, double totalPrice, Calendar buyDate){



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
            }else if(name.compareTo(midP.getName()) > 0){
                left = mid+1;
            }else if(name.compareTo(midP.getName()) <  0) {
                right = mid-1;
            }

        }

        throw new ObjectNotFoundException("No se encontro el objeto");

    }

    public void sort(){
        Collections.sort(products);
    }

    private void bubbleSortDescendingOrder() {
        for (int i = 0; i < products.size(); i++) {
            for (int j = 1; j < products.size()-i; j++) {
                if(products.get(j).compareTo(products.get(j-1))>0){
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
        aux=new ArrayList<>();
    }

    public void searchbyrange(int min, int max, int option) {
        searchbyrange(min, max, option);
        switch (option) {
            case 1:
                Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
                for (Product p:products) {
                    if(p.getPrice()>=min && p.getPrice()<=max){
                        aux.add(p);
                    }
                }
                break;
            case 2:
                Collections.sort(products, Comparator.comparingInt(Product::getInventory));
                for (Product p:products) {
                    if(p.getInventory()>=min && p.getInventory()<=max){
                        aux.add(p);
                    }
                }
                break;
            case 3:
                Collections.sort(products, Comparator.comparingInt(Product::getTimesBought));
                for (Product p:products) {
                    if(p.getTimesBought()>=min && p.getTimesBought()<=max){
                        aux.add(p);
                    }
                }
                break;
        }
        String n="";
        for (Product p:aux) {
            n+=p.toString()+"\n";
        }
    }
}
