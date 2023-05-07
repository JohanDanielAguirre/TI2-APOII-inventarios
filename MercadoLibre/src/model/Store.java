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
    private final Comparator<Delivery>[] comparators2;
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

        comparators[4] = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.compareTo(o2);
            }
        };

        comparators[5] = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getInventory() - o2.getInventory();
            }
        };

    }
    public void fillComparators2(){

        comparators2[0] = new Comparator<Delivery>() {
            @Override
            public int compare(Delivery o1, Delivery o2) {
                return o1.getBuyerName().compareTo(o2.getBuyerName());
            }
        };

        comparators2[1] = new Comparator<Delivery>() {
            @Override
            public int compare(Delivery o1, Delivery o2) {
                return (int) (o1.getTotalPrice() - o2.getTotalPrice());
            }
        };

        comparators2[2] = new Comparator<Delivery>() {
            @Override
            public int compare(Delivery o1, Delivery o2) {
                return o1.getBuyDate().compareTo(o2.getBuyDate());
            }
        };

    }
    public void modifyDelivery(int op,String name,String buyerName,int requested){
        switch (op){
            case 1:
                addProductToDelivery(name,buyerName,requested);
                break;
            case 2:
                removeProductOfDelivery(name,buyerName, requested);
                break;
        }
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
    public Delivery createDelivery(String buyerName, String productName,int requested) throws ObjectNotFoundException, NotEnoughProductsException{
        Product product = new Product(productName,"",0,0,0,0);
        Delivery d= null;

        int i = searchProductSpecific(0,product);
        products.get(i).setInventory(-requested);
        d= new Delivery(buyerName, LocalDateTime.now());
        deliveries.add(d);
        Collections.sort(deliveries);
        addProductToDelivery(productName,buyerName,requested);

        return d;

    }

    public void addProductToDelivery(String name,String buyerName,int requested){
        Product product = new Product(name,"",0,0,1,0);
        Delivery delivery = new Delivery(buyerName,null);
        try {
            int i = searchProductSpecific(0,product);
            int j = searchDeliverySpecific(0,delivery);
            for (int k = 0; k < requested; k++) {
                deliveries.get(j).addProducts(products.get(i));
                products.get(i).setTimesBought();
            }

            if (products.get(i).getInventory() <= 0){
                deleteProduct(product.getName());
            }
            
        } catch (ObjectNotFoundException | InvalidDataException e) {
            System.out.println(e.getMessage());
        }
                                                                                                                                                                                

    }

    public void removeProductOfDelivery(String name,String buyerName, int request){
        Delivery delivery = new Delivery(buyerName,null);
        try {
            int j = searchDeliverySpecific(0,delivery);
            deliveries.get(j).removeProduct(name,request);
        } catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
        }


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

        throw new ObjectNotFoundException("No se encontro el objeto Producto");

    }

    public int searchDeliverySpecific(int compare, Delivery product) throws ObjectNotFoundException {
        int left = 0;

        int right = deliveries.size()-1;

        while(left <= right){

            int mid = (left+right)/2;

            Delivery midP = deliveries.get(mid);

            if (comparators2[compare].compare(product,midP) == 0){
                return mid;
            }else if(comparators2[compare].compare(product,midP) > 0){
                left = mid+1;
            }else if(comparators2[compare].compare(product,midP) <  0) {
                right = mid-1;
            }

        }

        throw new ObjectNotFoundException("No se encontro el objeto Pedido");

    }

    public void sortProducts(){
        Collections.sort(products);
    }

    public void sortResultsByName(){
        Collections.sort(aux,comparators[0]);
    }

    public void sortResultsByPrice(){
        Collections.sort(aux,Comparator.comparingDouble(Product::getPrice));
    }

    public void sortResultsByTimesBought(){
        Collections.sort(aux,Comparator.comparingInt(Product::getTimesBought));
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
        comparators = new Comparator[6];
        comparators2 = new Comparator[3];
        fillComparators();
        fillComparators2();
    }

    public void searchbyrange(int min, int max, int option) {

        Product productMin;
        Product productMax;

        switch (option) {
            case 1:
                Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
                productMin = new Product("","", min,0,1,0);
                productMax = new Product("","", max,0,1,0);
                binarySearchRange(1,productMin,productMax);
                break;
            case 2:
                Collections.sort(products, Comparator.comparingInt(Product::getInventory));
                productMin = new Product("","", 0,min,1,0);
                productMax = new Product("","", 0,max,1,0);
                binarySearchRange(5,productMin,productMax);
                break;
            case 3:
                Collections.sort(products, Comparator.comparingInt(Product::getTimesBought));
                productMin = new Product("","", 0,0,1,min);
                productMax = new Product("","", 0,0,1,max);
                binarySearchRange(3,productMin,productMax);
                break;
        }

    }

    public void binarySearchRange(int compare, Product productMin, Product productMax) {
            // Ordenamos la lista en orden ascendente según el precio de los productos

            // Encontramos el índice del primer producto que tiene un precio mayor o igual a minPrice
            int startIndex = binarySearchStartIndex(compare,productMin);

            // Encontramos el índice del último producto que tiene un precio menor o igual a maxPrice
            int endIndex = binarySearchEndIndex(compare, productMax);

            // Creamos una nueva lista con los productos cuyo precio está dentro del rango especificado
            for (int i = startIndex; i <= endIndex; i++) {
                aux.add(products.get(i));
            }
    }


    private int binarySearchStartIndex(int compare, Product product) {
            int left = 0;
            int right = products.size() - 1;
            int result = products.size();
            while (left <= right) {
                int mid = left + (right - left) / 2;

                Product midP = products.get(mid);

                if (comparators[compare].compare(product,midP) >= 0) {
                    result = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return result;
    }

// Función auxiliar para encontrar el índice del último producto cuyo precio es menor o igual a maxPrice
    private int binarySearchEndIndex(int compare, Product product) {
            int left = 0;
            int right = products.size() - 1;
            int result = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;

                Product midP = products.get(mid);
                if (comparators[compare].compare(product,midP) <= 0) {
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

    public  ArrayList<Product> searchByRange(char start, char end) {
        // Ordena los productos en base a sus nombres
        products.sort(Comparator.comparing(Product::getName));

        // Encuentra el índice del primer producto que empieza por la letra inicial dada
        int startIndex = binarySearchStart(products, start);

        // Si no se encuentra ningún producto, no hay nada que hacer
        if (startIndex == -1) {
            return aux;
        }

        // Encuentra el índice del último producto que termina por la letra final dada
        int endIndex = binarySearchEnd(products, end);

        // Si no se encuentra ningún producto, no hay nada que hacer
        if (endIndex == -1) {
            return aux;
        }

        // Recorre los productos entre los índices encontrados y añádelos a los resultados
        for (int i = startIndex; i <= endIndex; i++) {
            aux.add(products.get(i));
        }

        return aux;
    }

    private static int binarySearchStart(ArrayList<Product> products, char start) {
        int low = 0;
        int high = products.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            char firstChar = products.get(mid).getName().charAt(0);

            if (firstChar == start) {
                // El primer producto que empieza por la letra inicial dada
                return mid;
            } else if (firstChar < start) {
                // La letra inicial dada está más adelante en el alfabeto, busca en la mitad derecha
                low = mid + 1;
            } else {
                // La letra inicial dada está más atrás en el alfabeto, busca en la mitad izquierda
                high = mid - 1;
            }
        }

        // No se ha encontrado ningún producto que empiece por la letra inicial dada
        return -1;
    }

    private static int binarySearchEnd(ArrayList<Product> products, char end) {
        int low = 0;
        int high = products.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            char lastChar = products.get(mid).getName().charAt(products.get(mid).getName().length() - 1);

            if (lastChar == end) {
                // El último producto que termina por la letra final dada
                return mid;
            } else if (lastChar < end) {
                // La letra final dada está más adelante en el alfabeto, busca en la mitad derecha
                low = mid + 1;
            } else {
                // La letra final dada está más atrás en el alfabeto, busca en la mitad izquierda
                high = mid - 1;
            }
        }

        // No se ha encontrado ningún producto que termine por la letra final dada
        return -1;
    }
}
