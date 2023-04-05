package ui;
import java.util.Scanner;
public class Main {
   public static  Scanner sc;
   public  static boolean exit = false;
    public static void main(String[] args) {
       sc = new Scanner(System.in);
       mainMenu();
    }

    public static void mainMenu(){
        System.out.println("""
                
                +++++++++++++++++++++++++
                WELCOME TO MERCADO LIBRE
                +++++++++++++++++++++++++
                
                  Select an option
                    [1] Login
                    [2] Register user
                
                """);
        String optionTemp = sc.nextLine();
        int option = Integer.parseInt(optionTemp);
        switch (option){
            case 1 : menuLogin();
            break;
            case 2 : registerUser();
            break;
        }
    }
    public static void menuLogin(){
        System.out.println("Insert userName\n" );
        String username = sc.nextLine();
        System.out.println("insert password\n");
        String password = sc.nextLine();


        // Aqui va la validacion para entrar a la pagina ya sea como comprador como vendedor

        if(true) { // si la validaciones correcta acceder al menu seleccionado
            System.out.println("""
                    \n
                    *****************
                    Redirecting to  Dashboard...
                    *****************
                    \s
                    """
            );
        }
        else{ //si es incorecta retorna al menu princiapl
            System.out.println("""
                        *******************
                        *BACK TO MAIN MENU*
                        *******************
                    """);
            mainMenu();
        }

    }
    public static void dashboardBuyer(){ // el usuario comprador va a tener un menu diferente al vendedor, ya que el comprador solo le interesa ver el producto y comprarlo
        System.out.println("\n **************** BUYER USER *****************\n" +
                           "our stocks" +
                           "\n buscar productos ");
        // aqui van el estock disponible

        System.out.println("""
                    [1] carrito
                    [2] efectuar pago
                    [3] logout
                """);
        String optionTemp =sc.nextLine();
        int option = Integer.parseInt(optionTemp);
        while (!exit){
            switch (option){
                case 1:
                    System.out.println("select the object that u wanna buy");
                    //pienso hacer otro switch donde el usuario seleccione los productos que quiera y cuando efectue el pago se descuenten del stock
                    break;
                case 2:
                    // aqui va la validacion del pago
                    break;
                case 3:
                    exit = true;
                    mainMenu();
                    break;
            }
        }

    }
    public static void dashboardSeller(){// este usuario va a ser para los vendedores donde van a agregar o quitar productos
        System.out.println("""
                ***********seller user*************
                [1] Show stocks
                [2] add any product
                [3] delete any product
                [4]logout
                """);
        String optionTemp =sc.nextLine();
        int option = Integer.parseInt(optionTemp);
        while (!exit){
            switch (option){
                case 1:
                   //mostrar los stock disponibles y sin existencias

                    break;
                case 2:
                    System.out.println("Name of product");
                    String ProductName = sc.nextLine();
                    System.out.println("Descrption");
                    String description = sc.nextLine();
                    System.out.println("Price");
                    String priceTemp =sc.nextLine();
                    double price = Double.parseDouble(priceTemp);
                    System.out.println("number of stocks");
                    String stockTemp = sc.nextLine();
                    int stock = Integer.parseInt(stockTemp);

                    // la relacion con el controller para guardar los datos

                    break;
                    case 3:
                        //borrar cantiades de productos
                break;
                case 4:
                    exit =true;
                    mainMenu();
                    break;

            }
        }
    }
    public static void registerUser(){
        System.out.println("name");
        String name = sc.nextLine();
        System.out.println("Username");
        String UserName = sc.nextLine();
        System.out.println("password");
        String password = sc.nextLine();
        System.out.println("""
                create account as
                    [1] seller
                    [2] buyer
                """);
        String typeTemp = sc.nextLine();
        int type = Integer.parseInt(typeTemp);
        // se llama al controller para crear el objeto user

        System.out.println("ur user was created sucessfully" +
                           "\n return to main menu...." +
                           "\n *******************");
        mainMenu();
    }
    }
