package ui;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import com.google.gson.Gson;
import exceptions.NotEnoughProductsException;
import exceptions.ObjectNotFoundException;
import model.Product;
import model.Store;
import model.Delivery;
import java.util.Arrays;

public class Main {
   public Scanner sc;
   public boolean exit = false;

   private Store controller;
   private Gson gson;

    public static void main(String[] args) {
        Main objMain = new Main();

        objMain.mainMenu();
    }

    public Main() {

        sc = new Scanner(System.in);
        gson = new Gson();
        controller = new Store();

    }

    public void mainMenu() {
        try {
            System.out.println("\n" +
                    "                                   \n" +
                    "    +++++++++++++++++++++++++ \n" +
                    "    WELCOME TO MERCADO LIBRE \n" +
                    "    +++++++++++++++++++++++++ \n" +
                    "                                         \n" +
                    "       Select an option \n" +
                    "         [1] Manage products\n" +
                    "         [2] Manage deliveries\n" +
                    "         [3] Search products\n"
            );
            String optionTemp = sc.nextLine();
            int option = Integer.parseInt(optionTemp);
            switch (option) {
                case 1:
                    System.out.println("\n" +
                            "                                   \n" +
                            "    --------------------------------------- \n" +
                            "    WELCOME TO MERCADO LIBRE PRODUCTS MODULE \n" +
                            "    --------------------------------------- \n" +
                            "                                         \n" +
                            "       Select an option \n" +
                            "         [1] create product\n" +
                            "         [2] delete product\n" );
                    optionTemp = sc.nextLine();
                    option = Integer.parseInt(optionTemp);
                    if(option==1){
                        System.out.println("please enter the name");
                        String n=sc.nextLine();
                        System.out.println("please enter the description");
                        String d=sc.nextLine();
                        System.out.println("please enter the price");
                        double p=sc.nextDouble();
                        System.out.println("please put the cuantity");
                        int c=sc.nextInt();
                        System.out.println("\n" +
                                "                                   \n" +
                                "    -------------------------- \n" +
                                "    PLEASE SELECT THE CATEGORY \n" +
                                "    -------------------------- \n" +
                                "                                         \n" +
                                "       Select an option \n" +
                                "         [1] BOOKS\n" +
                                "         [2] ELECTRONIC\n"+
                                "         [3] CLOTHES AND ACCESORIES\n"+
                                "         [4] FOOD AND DRINKS\n"+
                                "         [5] STATIONERY\n"+
                                "         [6] SPORTS\n"+
                                "         [7] PERSONAL CARE AND BEAUTY\n"+
                                "         [8] GAMES AND TOYS\n");
                        int cat=sc.nextInt();
                        System.out.println("please put the times the product has brought ");
                        int b=sc.nextInt();
                        controller.addProducts(n,d,p,c,cat,b);
                    } else if (option==2) {
                        System.out.println("please enter the name of the product will be eliminated");
                        String delete= sc.nextLine();
                        controller.deleteProduct(delete);
                    }
                    break;
                case 2:
                    System.out.println("\n" +
                            "                                   \n" +
                            "    ----------------------------------------- \n" +
                            "    WELCOME TO MERCADO LIBRE DELIVERYS MODULE \n" +
                            "    ----------------------------------------- \n" +
                            "                                         \n" +
                            "       Select an option \n" +
                            "         [1] create delivery\n" +
                            "         [2] delete product from delivery\n" );
                    optionTemp = sc.nextLine();
                    option = Integer.parseInt(optionTemp);
                    if(option==1){
                        System.out.println("please put the name of the product");
                        String np=sc.nextLine();
                        System.out.println("please put the name of the buyer");
                        String nb=sc.nextLine();
                        System.out.println("put the cuantity of the product inside the delivery");
                        int c=sc.nextInt();
                        controller.createDelivery(nb,np,c);
                    } else if (option==2) {
                        System.out.println("please put the name of the product");
                        String np=sc.nextLine();
                        System.out.println("please put the name of the buyer");
                        String nb=sc.nextLine();
                        System.out.println("put the cuantity of be removed or 0 if you want eliminate the product totally");
                        int c=sc.nextInt();
                        controller.removeProductOfDelivery(np,nb,c);
                    }

                    break;
                case 3:
                    System.out.println("\n" +
                            "                                   \n" +
                            "    --------------------------------------- \n" +
                            "    WELCOME TO MERCADO LIBRE SEARCH MODULE \n" +
                            "    --------------------------------------- \n" +
                            "                                         \n" +
                            "       Select an option \n" +
                            "         [1] search by price\n" +
                            "         [2] search by quantity\n" +
                            "         [3] search by time bought\n"+
                            "         [4] search by letter range\n"+
                            "         [5] search by prefix\n");
                    controller.refreshaux();
                    optionTemp = sc.nextLine();
                    option = Integer.parseInt(optionTemp);
                    if(option>=1 && option<=3){
                        System.out.println("ingrese el valor minimo y maximo");
                        String tmin = sc.nextLine();
                        String tmax = sc.nextLine();
                        int min = Integer.parseInt(tmin);
                        int max = Integer.parseInt(tmax);
                        controller.searchbyrange(min, max, option);
                    } else if (option==4 ) {
                        System.out.println("ingrese la letra inicial y la letra final");
                        String tmin = sc.nextLine();
                        String tmax = sc.nextLine();
                        char min=tmin.charAt(0);
                        char max=tmax.charAt(0);
                        controller.searchByRange(min,max);
                    } else if (option==5) {
                        System.out.println("ingrese la letra inicial y la letra final");
                        String tmin = sc.nextLine();
                        String tmax = sc.nextLine();
                        controller.searchInRange(tmin,tmax);
                    }
                    System.out.println("\n" +
                            "                                   \n" +
                            "    ------------------------------------------ \n" +
                            "    SELECT A ORDER METHOD FOR YOUR INFORMATION  \n" +
                            "    ------------------------------------------ \n" +
                            "                                         \n" +
                            "       Select an option \n" +
                            "         [1] ascendant\n" +
                            "         [2] descendent\n");
                    optionTemp = sc.nextLine();
                    option = Integer.parseInt(optionTemp);
                    String s=controller.order(option);
                    System.out.println(s);
                    break;
            }

        } catch (NumberFormatException | ObjectNotFoundException | NotEnoughProductsException e) {
            System.out.println("algo incorrecto se ingreso por favor vuelva a intentarlo");
        }
    }

    public void writeGsonProducts(){

        String json = gson.toJson(controller.products);

        try{
            FileOutputStream fos = new FileOutputStream(new File("countries.txt"));
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readGsonProducts(){

        File file = new File("countries.txt");

        if (file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);

                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

                String json = "";
                String line;

                if ((line = reader.readLine())!=null){
                    json = line;
                }

                fis.close();

                Product[] products = gson.fromJson(json, Product[].class);

                controller.products.addAll(Arrays.asList(products));

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
