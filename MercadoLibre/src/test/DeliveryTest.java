package test;

import exceptions.InvalidDataException;
import junit.framework.TestCase;
import model.Delivery;
import model.Product;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class DeliveryTest extends TestCase {


    private Delivery delivery;

    private void setUpStage3(){
        LocalDateTime now = LocalDateTime.now();
        delivery = new Delivery("Juan Sebastian", now);


        try {
            delivery.addProducts(new Product("ObjProduct1", "Increible producto", 500000,45,4,25));
            delivery.addProducts(new Product("ObjProduct2", "Producto chino", 400,4,3,12));
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }

    }

    public void testAddObjectNoException(){
        setUpStage3();

        Product objProduct3 = new Product("ObjProduct3", "Increible producto 2", 2000,45,4,25);

        try {
            delivery.addProducts(objProduct3);
            assertEquals(502400.0,delivery.getTotalPrice());
        }catch (InvalidDataException e){
            fail();
        }
        
    }
    public void testAddObjectWithException(){
        setUpStage3();

        Product objProduct3 = new Product("ObjProduct3", "Increible producto 2", -2000,45,4,25);

        try {
            delivery.addProducts(objProduct3);

            fail();
        }catch (InvalidDataException e){
            assertNotNull(e);
        }

    }

    public void testRemoveProductObjectExists(){
        setUpStage3();

        delivery.removeProduct(1);

        assertEquals(500000.0,delivery.getTotalPrice());
    }
    public void testRemoveProductObjectNotExists(){
        setUpStage3();

        delivery.removeProduct(-1);

        assertEquals(500400.0,delivery.getTotalPrice());
    }

}
