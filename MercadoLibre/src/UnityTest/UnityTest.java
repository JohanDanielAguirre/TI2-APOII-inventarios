package UnityTest;
import junit.framework.TestCase;
import model.Controller;

public class UnityTest extends TestCase{
    public void testAgregarComprador() {
   Controller controller = new Controller();
   boolean valorRetorno = controller.agregarComprador("usuario1", "contraseña1", "Nombre del usuario");
   assertTrue(valorRetorno);
}

public void testAgregarUsuarioConMismaClave() {
   Controller controller = new Controller();
   controller.agregarComprador("usuario1", "contraseña1", "Nombre del usuario");
   boolean valorRetorno = controller.agregarComprador("usuario1", "contraseña1", "Nombre del usuario2");
   assertTrue(valorRetorno);
}

public void testAgregarUsuarioConClaveVacia() {
   Controller controller = new Controller();
   boolean valorRetorno = controller.agregarComprador("usuario1", "", "Nombre del usuario3");
   assertTrue(valorRetorno);
}
   public void testAgregarVendedor() {
      Controller controller = new Controller();
      boolean valorRetorno = controller.agregarVendedor("usuario1", "contraseña1", "Nombre del usuario");
      assertTrue(valorRetorno);
   }




}
