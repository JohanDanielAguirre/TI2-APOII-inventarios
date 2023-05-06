package ui;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import com.google.gson.Gson;
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
                    break;
                case 2:
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
                            "         [3] search by time bought\n");
                    optionTemp = sc.nextLine();
                    option = Integer.parseInt(optionTemp);
                    System.out.println("ingrese el valor minimo y maximo");
                    String tmin = sc.nextLine();
                    String tmax = sc.nextLine();
                    int min = Integer.parseInt(tmin);
                    int max = Integer.parseInt(tmax);
                    controller.searchbyrange(min, max, option);
                    break;
            }

        } catch (NumberFormatException e) {
            System.out.println("algo incorrecto se ingreso por favor vuelva a intentarlo");
        }
    }

    public void writeGsonDeliveries(){

        String json = gson.toJson(controller.deliveries);

        try{
            FileOutputStream fos = new FileOutputStream(new File("countries.txt"));
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readGsonDeliveries(){

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

                Delivery[] countriesFromJson = gson.fromJson(json, Delivery[].class);

                controller.deliveries.addAll(Arrays.asList(countriesFromJson));

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
