package UnityTest;
import junit.framework.TestCase;
import model.Controller;

public class UnityTest extends TestCase{
    public void testAgregarUsuario() {
   Controller controller = new Controller();
   boolean valorRetorno = controller.agregarComprador("usuario1", "contraseña1", "Nombre del usuario");
   assertTrue(valorRetorno);
}

public void testAgregarUsuarioConMismaClave() {
   Controller controller = new Controller();
   controller.agregarComprador("usuario1", "contraseña1", "Nombre del usuario");
   boolean valorRetorno = controller.agregarComprador("usuario1", "contraseña1", "Nombre del usuario2");
   assertFalse(valorRetorno);
}

public void testAgregarUsuarioConClaveVacia() {
   Controller controller = new Controller();
   boolean valorRetorno = controller.agregarComprador("usuario1", "", "Nombre del usuario3");
   assertFalse(valorRetorno);
}




}
