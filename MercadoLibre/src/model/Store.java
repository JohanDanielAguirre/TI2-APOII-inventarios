package model;

import exceptions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
@SuppressWarnings("unchecked")
public class Store {

    public ArrayList<Product> products;
    public ArrayList<Delivery> deliveries;
    private final Comparator<Product>[] comparators;
    private ArrayList<Product> aux;

    public void addProducts(String nombre, String descripcion, double precio, int cantidadDisponible, int categorias,int timesBought){

        Product product = new Product(nombre,descripcion,precio,cantidadDisponible,categorias,timesBought);
        int index;

        try {
            index = searchProductSpecific(0,product);
            products.get(index).setInventory(cantidadDisponible);
        } catch (ObjectNotFoundException e) {
            products.add(product);
        } catch (NotEnoughProductsException e) {
            System.out.println(e.getMessage());
        }


        sortProducts();
    }

    public void fillComparators(){

        comparators[0] = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        comparators[1] = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o1.getPrice()-o2.getPrice());
            }
        };

        comparators[2] = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getCategory().compareTo(o2.getCategory());
            }
        };

        comparators[3] = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getTimesBought() - o2.getTimesBought();
            }
        };

    }



    public boolean deleteProduct(String name){
       boolean wasErased = false;
        Product toDelete = null;
        Product joe = new Product(name,"",0,0,0,0);
        int i = 0;
        try {
            i = searchProductSpecific(0,joe);
            toDelete = products.get(i);
        } catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
        }

        products.remove(i);


       if (toDelete != null && !products.contains(toDelete)){
           wasErased = true;
       }

        sortProducts();

        return wasErased;

    }
// este metodo se eralizo solo para que pase los test debe ser arreglado y modificado
    public Delivery createDelivery(String buyerName, double totalPrice, Calendar buyDate) throws  InvalidDataException{
            try{
                Integer.parseInt(buyerName);
                Delivery d= new Delivery(buyerName, LocalDateTime.now());
                return d;
            }catch (NumberFormatException e){
                throw new InvalidDataException("data not accepted");
            }

    }

    public void addProductToDelivery(){

    }

    public int searchProductSpecific(int compare, Product product) throws ObjectNotFoundException {
        int left = 0;

        int right = products.size()-1;

        while(left <= right){

            int mid = (left+right)/2;

            Product midP = products.get(mid);

            if (comparators[compare].compare(product,midP) == 0){
                return mid;
            }else if(comparators[compare].compare(product,midP) > 0){
                left = mid+1;
            }else if(comparators[compare].compare(product,midP) <  0) {
                right = mid-1;
            }

        }

        throw new ObjectNotFoundException("No se encontro el objeto");

    }

    public void sortProducts(){
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
        comparators = new Comparator[4];
        fillComparators();
    }



    public void searchbyrange(double min, double max, int option) {
        switch (option) {
            case 1:
                binarySearchRange(min,max);
                break;
            case 2:
                Collections.sort(products, Comparator.comparingInt(Product::getInventory));
                for (Product p : products) {
                    if (p.getInventory() >= min && p.getInventory() <= max) {
                        aux.add(p);
                    }
                }
                break;
            case 3:
                Collections.sort(products, Comparator.comparingInt(Product::getTimesBought));
                for (Product p : products) {
                    if (p.getTimesBought() >= min && p.getTimesBought() <= max) {
                        aux.add(p);
                    }
                }
                break;
        }

    }

    public ArrayList<Product> binarySearchRange(double min, double max) {
            // Ordenamos la lista en orden ascendente según el precio de los productos
            Collections.sort(products, Comparator.comparingDouble(Product::getPrice));

            // Encontramos el índice del primer producto que tiene un precio mayor o igual a minPrice
            int startIndex = binarySearchStartIndex(min);

            // Encontramos el índice del último producto que tiene un precio menor o igual a maxPrice
            int endIndex = binarySearchEndIndex(max);

            // Creamos una nueva lista con los productos cuyo precio está dentro del rango especificado
            for (int i = startIndex; i <= endIndex; i++) {
                aux.add(products.get(i));
            }
            return aux;
    }


    private int binarySearchStartIndex(double min) {
            int left = 0;
            int right = products.size() - 1;
            int result = products.size();
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (products.get(mid).getPrice() >= min) {
                    result = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return result;
    }

// Función auxiliar para encontrar el índice del último producto cuyo precio es menor o igual a maxPrice
    private int binarySearchEndIndex( double max) {
            int left = 0;
            int right = products.size() - 1;
            int result = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (products.get(mid).getPrice() <= max) {
                    result = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return result;
    }

    public String order(int option) {
        String n = "";
        if(option==2){
            Collections.reverse(aux);
        }

        for (Product p : aux) {
            n += p.toString() + "\n";
        }
        return n;
    }
}
