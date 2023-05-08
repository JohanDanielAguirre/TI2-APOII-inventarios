package test;

import exceptions.*;
import junit.framework.TestCase;
import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;


public class StoreTest extends TestCase {

    private Store store;

    private void setUpStage6() {
        store = new Store();
    }

    private void setUpStage5() {
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
                40001,
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
                360001,
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

    private void setUpStage4() {
        setUpStage5();

        try {
            store.createDelivery("Juan Sebastián L", "Anillo de plata tematica juego Zelda", 1);
            store.createDelivery("Johan Daniel Aguirre", "Silla de escritorio ergonomica", 1);
            store.createDelivery("Paola Andrea", "Termo de agua 2L", 1);
            store.createDelivery("Larry kapija", "Vajilla IMUSA Cerámica", 1);
            store.createDelivery("Marian Camille Merch", "HP Pavillion mini 2012", 1);
        } catch (ObjectNotFoundException | NotEnoughProductsException e) {
            throw new RuntimeException(e);
        }


    }

    public void testAddProductNew() {
        setUpStage5();


        Product toAdd = new Product("Vaso de cristal", "Vaso super fino", 250000, 250, 4, 0);

        store.addProducts("Vaso de cristal", "Vaso super fino", 250000, 250, 4, 0);

        try {
            int index = store.searchProductSpecific(0, toAdd);
            assertEquals(toAdd.getName(), store.products.get(index).getName());
        } catch (ObjectNotFoundException e) {
            fail();
        }


    }

    public void testAddProductExist() {
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
            int index = store.searchProductSpecific(0, product);
            assertEquals(275, store.products.get(index).getInventory());
        } catch (ObjectNotFoundException e) {
            fail();
        }

    }

    public void testRemoveProductExists() {
        setUpStage5();
        String name = "Bolso Louis Vuitton";

        assertTrue(store.deleteProduct(name));
    }

    public void testRemoveProductNotExists() {
        setUpStage5();
        String name = "Juguete de max steel";

        assertFalse(store.deleteProduct(name));
    }

    public void testcreatedelivery() {
        setUpStage5();
        setUpStage4();

        try {
            Delivery d = store.createDelivery("johan agirre", "HP Pavillion mini 2012", 1);
            int i = store.searchDeliverySpecific(0, d);
            assertEquals(d, store.deliveries.get(i));
        } catch (ObjectNotFoundException | NotEnoughProductsException e) {
            fail();
        }

    }


    public void testcreatedeliveryfail() {
        setUpStage5();
        setUpStage4();
        try {
            Delivery d = store.createDelivery("Pedro el tonto", "yo mismo", 999);
            fail();
        } catch (ObjectNotFoundException | NotEnoughProductsException e) {
            assertNotNull(e);
        }
    }


    public void testmodifycorrectlyAdd() {
        setUpStage5();
        setUpStage4();

        try {
            store.modifyDelivery(1, "Tarjeta de sonido profesional", "Johan Daniel Aguirre", 3);
            int i = store.searchDeliverySpecific(0, new Delivery("Johan Daniel Aguirre", null));
            Delivery delivery = store.deliveries.get(i);
            int i2 = delivery.searchProduct("Tarjeta de sonido profesional");
            assertNotNull(delivery.getProducts().get(i2));
        } catch (ObjectNotFoundException e) {
            fail();
        }
    }

    public void testmodifycorrectlyRemove() {
        setUpStage5();
        setUpStage4();

        try {
            store.modifyDelivery(1, "Tarjeta de sonido profesional", "Johan Daniel Aguirre", 2);
            store.modifyDelivery(2, "Tarjeta de sonido profesional", "Johan Daniel Aguirre", 2);
            int i = store.searchDeliverySpecific(0, new Delivery("Johan Daniel Aguirre", null));
            Delivery delivery = store.deliveries.get(i);
            int i2 = delivery.searchProduct("Tarjeta de sonido profesional");
            fail();
        } catch (ObjectNotFoundException e) {
            assertNotNull(e);
        }
    }

    public void testModifyAddFail() {
        setUpStage5();
        setUpStage4();

        try {
            store.modifyDelivery(1, "jojo andres", "Johan Daniel Aguirre", 3);
            int i = store.searchDeliverySpecific(0, new Delivery("Johan Daniel Aguirre", null));
            Delivery delivery = store.deliveries.get(i);
            int i2 = delivery.searchProduct("Tarjeta de sonido profesional");
            fail();
        } catch (ObjectNotFoundException e) {
            assertNotNull(e);
        }
    }

    public void testOrganizeListAscending() {
        setUpStage5();
        ArrayList<Product> products = store.products;
        Collections.sort(products);
        store.sortProducts(store.products);

        assertEquals(products, store.products);


    }

    public void testOrganizeProductsListDescending() {
        setUpStage5();

        ArrayList<Product> products = store.products;
        Collections.sort(products);
        Collections.reverse(products);
        store.bubbleSortDescendingOrder();

        assertEquals(products, store.products);


    }

    public void testSearchByNameProduct() {
        setUpStage5();
        Product product = new Product("Termo de agua 2L",
                "Termo de agua en diferentes colores y aun excelente precio. Somos una tienda de verificada",
                40000.0,
                10024,
                3,
                30);

        try {
            int i = store.searchProductSpecific(0, product);
            assertEquals(product.getDescription(), store.products.get(i).getDescription());
        } catch (ObjectNotFoundException e) {
            fail();
        }

    }

    public void testsearchbyCategory() {
        setUpStage5();

        ArrayList<Product> a = new ArrayList<>();
        try {
            store.searchbyrange(3, 3, 4);
            a = store.getAux();
            assertEquals(5, a.size());
        } catch (Exception e) {
            fail();
        }


    }

    public void testsearchbyPrice() {
        setUpStage5();
        ArrayList<Product> a = new ArrayList<>();
        try {
            store.searchbyrange(40000, 40000, 1);
            a = store.getAux();
            assertEquals(1, a.size());
        } catch (Exception e) {
            fail();
        }
    }

    public void testSearchByNameProductfail() {
        setUpStage5();
        Product product = new Product("Disco de pavlov",
                "hermoso disco del videojuego pavlov compatible con todas las vr del mercado",
                250000,
                9999,
                2,
                0);

        try {
            int i = store.searchProductSpecific(0, product);
            fail();
        } catch (ObjectNotFoundException e) {
            assertNotNull(e);
        }

    }

    public void testsearchbyBoughttime() {
        setUpStage5();
        ArrayList<Product> a = new ArrayList<>();
        try {
            store.searchbyrange(30, 30, 3);
            a = store.getAux();
            assertEquals(2, a.size());
        } catch (Exception e) {
            fail();
        }
    }

    public void testSearchByNameDelivery() {
        setUpStage5();
        setUpStage4();

        Delivery delivery = new Delivery("Juan Sebastián L", null);

        try {
            int i = store.searchDeliverySpecific(0, delivery);
            assertEquals(delivery.getBuyerName(), store.deliveries.get(i).getBuyerName());
        } catch (ObjectNotFoundException e) {
            fail();
        }

    }

    public void testSearchByNameNotFound() {
        setUpStage5();
        Product product = new Product("Disco Pavlo",
                "",
                0,
                0,
                0,
                0);

        try {
            int i = store.searchProductSpecific(0, product);
            fail();
        } catch (ObjectNotFoundException e) {
            assertNotNull(e);
        }

    }

    public void testSearchByRangeNumericError() {
        setUpStage5();

        int min = -90;
        int max = 400;

        try {
            store.searchbyrange(min, max, 1);
            fail();
        } catch (InvalidDataException e) {
            assertNotNull(e);
        }


    }

    public void testSearchByRange() {
        setUpStage5();

        int min = 200;
        int max = 400000;
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Bolso Louis Vuitton",
                "Bolso de alta gama con cuero suizo",
                250000,
                250,
                3,
                25));
        products.add(new Product("Vajilla IMUSA Cerámica",
                "Hermosa vajilla de excelentisima calidad",
                40001,
                50000,
                3,
                600));
        products.add(new Product("Termo de agua 2L",
                "Termo de agua en diferentes colores y aun excelente precio. Somos una tienda de verificada",
                40000,
                10024,
                3,
                30));
        products.add(new Product("Tarjeta de sonido profesional",
                "Tarjeta de sonido game blaster platinum para escuchar tus  juegos favoritos  a otro nivel",
                175000,
                25000,
                2,
                3600));
        products.add(new Product("Silla de escritorio ergonomica",
                "Silla de oficina marca lo mejor para tu espalda que ayudará a tu columna a liberar toda esa presión acumulada por el trabajo",
                360001,
                5000,
                3,
                350));
        products.add(new Product("Anillo de plata tematica juego Zelda",
                "Anillo de excelente calidad con diferentes logotipos de la temática del videojuego de Zelda",
                360000,
                5000,
                3,
                350));

        Collections.sort(products, Comparator.comparingDouble(Product::getPrice));

        try {
            store.searchbyrange(min, max, 1);
            assertEquals(products.toString(), store.getAux().toString());
        } catch (InvalidDataException e) {
            fail();
        }

    }

    public void testSearchByRangeAll() {
        setUpStage5();

        int min = 0;
        int max = 99999999;

        try {
            store.searchbyrange(min, max, 1);
            assertEquals(store.products, store.getAux());
        } catch (InvalidDataException e) {
            fail();
        }

    }

    public void testSearchByRangeNothing() {
        setUpStage5();

        int min = 0;
        int max = 10;
        ArrayList<Product> products = new ArrayList<>();
        try {
            store.searchbyrange(min, max, 1);
            assertEquals(products, store.getAux());
        } catch (InvalidDataException e) {
            fail();
        }

    }

    public void testSearchDeliveryExistn_t() {
        setUpStage5();
        setUpStage4();

        Delivery delivery = new Delivery("juanito alcachofa", null);

        try {
            int i = store.searchDeliverySpecific(0, delivery);

            fail();
        } catch (ObjectNotFoundException e) {
            assertNotNull(e);
        }


    }


    public void testSearchDeliveryName() {
        setUpStage5();
        setUpStage4();

        Delivery delivery = new Delivery("Paola Andrea", null);

        try {
            int i = store.searchDeliverySpecific(0, delivery);
            assertEquals(delivery.getBuyerName(), store.deliveries.get(i).getBuyerName());
        } catch (ObjectNotFoundException e) {
            fail();
        }
    }

    public void testSearchDeliveryByTotalPrice() {
        setUpStage5();
        setUpStage4();

        Delivery delivery = new Delivery("Juan Sebastián L", null);
        delivery.setTotalPrice2(360000);
        try {
            int i = store.searchDeliverySpecific(1, delivery);
            assertEquals(delivery.getBuyerName(), store.deliveries.get(i).getBuyerName());
        } catch (ObjectNotFoundException e) {
            fail();
        }

    }

    public void testsearchbyprefixwithelements() {
        setUpStage5();
        store.searchInRange("Ter", "2L");
        ArrayList<Product> a = new ArrayList<>(store.getAux());
        assertEquals(0, a.size());
    }


    public void testsearchletter() {
        setUpStage5();
        try {
            store.searchByRange('A', 'z');
        } catch (Exception e) {
            fail();
        }
        ArrayList<Product> a = new ArrayList<>(store.getAux());
        assertEquals(8, a.size());
    }

    public void testsearchletterandnumber() {
        setUpStage5();
        try {
            store.searchByRange('1', 'a');
            ArrayList<Product> a = new ArrayList<>(store.getAux());
            fail();
        } catch (InvalidDataException e) {
            assertNotNull(e);
        }

    }

    public void testsearchtwonumbers() {
        setUpStage5();
        try {
            store.searchByRange('9', '8');
            ArrayList<Product> a = new ArrayList<>(store.getAux());
            fail();
        } catch (InvalidDataException e) {
            assertNotNull(e);
        }

    }

    public void testsearchsymbols() {
        setUpStage5();
        try {
            store.searchByRange('*', '+');
            ArrayList<Product> a = new ArrayList<>(store.getAux());
            fail();
        } catch (InvalidDataException e) {
            assertNotNull(e);
        }


    }

    public void testorder1(){
        setUpStage4();
        store.orderdelivery(1);
        assertEquals("Johan Daniel Aguirre", store.deliveries.get(0).getBuyerName());
    }
    public void testorder2(){
        setUpStage4();
        store.orderdelivery(2);
        assertEquals("Paola Andrea", store.deliveries.get(0).getBuyerName());
    }

}
