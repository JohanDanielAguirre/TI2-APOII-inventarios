package test;

import exceptions.NotEnoughProductsException;
import junit.framework.TestCase;
import model.Product;

public class ProductTest extends TestCase {

    private Product product;

    private void setUpStage1(){
        product = new Product("Bolso Louis Vuitton","Bolso de alta gama con cuero suizo", 250000,250,3,25);
    }

    public void testAddInventoryNoException(){
        setUpStage1();

        try {
            product.setInventory(25);
            assertEquals(product.getInventory(),275);
        } catch (NotEnoughProductsException e) {
            fail();
        }


    }
    public void testRemoveInventoryNoException(){
        setUpStage1();

        try {
            product.setInventory(-25);
            assertEquals(product.getInventory(),225);
        } catch (NotEnoughProductsException e) {
            fail();
        }


    }

    public void testRemoveInventoryWithException(){
        setUpStage1();

        try {
            product.setInventory(-275);
            fail();
        } catch (NotEnoughProductsException e) {
            assertNotNull(e);
        }
    }
}
