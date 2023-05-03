package test;

import junit.framework.TestCase;
import model.Delivery;
import model.Product;

import java.util.GregorianCalendar;

public class DeliveryTest extends TestCase {


    private Delivery delivery;

    private void setUpStage3(){
        delivery = new Delivery("Juan Sebastian",500400,new GregorianCalendar());

        delivery.addProducts(new Product("ObjProduct1", "Increible producto", 500000,45,4,25));
        delivery.addProducts(new Product("ObjProduct2", "Producto chino", 400,4,3,12));
    }

    private void testRemoveProduct(){

        
    }

}
