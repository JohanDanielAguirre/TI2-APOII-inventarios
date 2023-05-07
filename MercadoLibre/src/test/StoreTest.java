package test;

import exceptions.*;
import junit.framework.TestCase;
import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class StoreTest extends TestCase {

    private Store store;
    private void setUpStage6(){
        store = new Store();
    }

    private void setUpStage5(){
        setUpStage6();

        store.addProducts("Bolso Louis Vuitton",
                "Bolso de alta gama con cuero suizo",
                250000,
                250,
                3,
                25);
        store.addProducts("HP Pavillion mini 2012",
                "computadora mini perfecta para llevar en el bolsillo con características técnicas que por su tamaño no aparenta, en perfecto estado se vende por motivo de viaje NO NEGOCIABLE",
                1500000,
                2,
                2,
                0);
        store.addProducts("Grafica AMD RX6800XT",
                "tarjetas gráficas baratas pues se usaron para mineria y ya no les puedo dar uso precio negociable",
                2000000,
                600,
                2,
                30);
        store.addProducts("Vajilla IMUSA Cerámica",
                "Hermosa vajilla de excelentisima calidad",
                40000,
                50000,
                3,
                600);
        store.addProducts("Termo de agua 2L",
                "Termo de agua en diferentes colores y aun excelente precio. Somos una tienda de verificada",
                40000,
                9999,
                3,
                30);
        store.addProducts("Termo de agua 2L",
                "Termo de agua de excelente calidad somos una importadora seria así que mejor  abstenerse de preguntar cosas innecesarias ",
                20000,
                25,
                3,
                2);
        store.addProducts("Tarjeta de sonido profesional",
                "Tarjeta de sonido game blaster platinum para escuchar tus  juegos favoritos  a otro nivel",
                175000,
                25000,
                2,
                3600);
        store.addProducts("Silla de escritorio ergonomica",
                "Silla de oficina marca lo mejor para tu espalda que ayudará a tu columna a liberar toda esa presión acumulada por el trabajo",
                360000,
                5000,
                3,
                350);
        store.addProducts("Anillo de plata tematica juego Zelda",
                "Anillo de excelente calidad con diferentes logotipos de la temática del videojuego de Zelda",
                360000,
                5000,
                3,
                350);

    }

    private void setUpStage4(){
        setUpStage6();
        setUpStage5();

        try {
            store.createDelivery("Juan Sebastián L" , "Anillo de plata tematica juego Zelda",1);
            store.createDelivery("Johan Daniel Aguirre" , "Silla de escritorio ergonomica",1);
            store.createDelivery("Paola Andrea" , "Termo de agua 2L",1);
            store.createDelivery("Larry kapija" , "Vajilla IMUSA Cerámica",1);
            store.createDelivery("Marian Camille Merch" , "HP Pavillion mini 2012",1);
        } catch (ObjectNotFoundException | NotEnoughProductsException e) {
            throw new RuntimeException(e);
        }



    }

    public void testAddProductNew(){
        setUpStage6();
        setUpStage5();


        Product toAdd = new Product("Vaso de cristal","Vaso super fino",250000,250,4,0);

        store.addProducts("Vaso de cristal","Vaso super fino",250000,250,4,0);

        try {
            int index = store.searchProductSpecific(0,toAdd);
            assertEquals(toAdd.getName(),store.products.get(index).getName());
        } catch (ObjectNotFoundException e) {
            fail();
        }



    }

    public void testAddProductExist(){
        setUpStage6();
        setUpStage5();
        Product product = new Product("Bolso Louis Vuitton",
                "Bolso de alta gama con cuero suizo",
                250000,
                25,
                3,
                25);
        store.addProducts("Bolso Louis Vuitton",
                "Bolso de alta gama con cuero suizo",
                250000,
                25,
                3,
                25);

        try {
            int index = store.searchProductSpecific(0,product);
            assertEquals(275,store.products.get(index).getInventory());
        } catch (ObjectNotFoundException e) {
            fail();
        }

    }

    public void testRemoveProductExists(){
        setUpStage6();
        setUpStage5();
        String name = "Bolso Louis Vuitton";

        assertTrue(store.deleteProduct(name));
    }

    public void testRemoveProductNotExists(){
        setUpStage6();
        setUpStage5();
        String name = "Juguete de max steel";

        assertFalse(store.deleteProduct(name));
    }

    public void testcreatedelivery(){
        setUpStage6();
        setUpStage5();
        setUpStage4();

        try {
            Delivery d=store.createDelivery("johan agirre","HP Pavillion mini 2012",1);
            int i = store.searchDeliverySpecific(0,d);
            assertEquals(d,store.deliveries.get(i));
        } catch (ObjectNotFoundException | NotEnoughProductsException e) {
            fail();
        }

    }


    public void testcreatedeliveryfail(){
        setUpStage6();
        setUpStage4();
        try {
            Delivery d=store.createDelivery("Pedro el tonto","yo mismo",999);
            fail();
        } catch (ObjectNotFoundException | NotEnoughProductsException e) {
            assertNotNull(e);
        }
    }


    public void testmodifycorrectlyAdd(){
        setUpStage6();
        setUpStage5();
        setUpStage4();

        try {
            store.modifyDelivery(1,"Tarjeta de sonido profesional","Johan Daniel Aguirre",3);
            int i = store.searchDeliverySpecific(0,new Delivery("Johan Daniel Aguirre",null));
            Delivery delivery = store.deliveries.get(i);
            int i2 = delivery.searchProduct("Tarjeta de sonido profesional");
            assertNotNull(delivery.getProducts().get(i2));
        }catch (Exception e){
            fail();
        }
    }

    public void testmodifycorrectlyRemove(){
        setUpStage6();
        setUpStage5();
        setUpStage4();

        try {
            store.modifyDelivery(1,"Tarjeta de sonido profesional","Johan Daniel Aguirre",2);
            store.modifyDelivery(2,"Tarjeta de sonido profesional","Johan Daniel Aguirre",2);
            int i = store.searchDeliverySpecific(0,new Delivery("Johan Daniel Aguirre",null));
            Delivery delivery = store.deliveries.get(i);
            int i2 = delivery.searchProduct("Tarjeta de sonido profesional");
            fail();
        }catch (ObjectNotFoundException e){
            assertNotNull(e);
        }
    }

    public void testModifyAddFail(){
        setUpStage6();
        setUpStage5();
        setUpStage4();

        try {
            store.modifyDelivery(1,"jojo andres","Johan Daniel Aguirre",3);
            int i = store.searchDeliverySpecific(0,new Delivery("Johan Daniel Aguirre",null));
            Delivery delivery = store.deliveries.get(i);
            int i2 = delivery.searchProduct("Tarjeta de sonido profesional");
            fail();
        }catch (ObjectNotFoundException e){
            assertNotNull(e);
        }
    }

    public void testOrganizeListAscending(){
        setUpStage6();
        setUpStage5();
        ArrayList<Product> products = store.products;
        Collections.sort(products);
        store.sortProducts(store.products);

        assertEquals(products,store.products);


    }

    public void testOrganizeProductsListDescending(){
        setUpStage6();
        setUpStage5();

        ArrayList<Product> products = store.products;
        Collections.sort(products);
        Collections.reverse(products);
        store.bubbleSortDescendingOrder();

        assertEquals(products,store.products);


    }

    public void testOrganizeDeliveriesListDescending(){

        setUpStage6();
        setUpStage5();
        setUpStage4();

        ArrayList<Delivery> deliveries = store.deliveries;



    }






}
