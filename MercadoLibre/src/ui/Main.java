package ui;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import com.google.gson.Gson;
import model.Controller;
import model.Delivery;
import java.util.Arrays;

public class Main {
   public Scanner sc;
   public boolean exit = false;

   private Controller controller;
   private Gson gson;

    public static void main(String[] args) {
        Main objMain = new Main();

        objMain.mainMenu();
    }

    public Main() {

        sc = new Scanner(System.in);
        gson = new Gson();
        controller = new Controller();

    }

    public void mainMenu(){
        System.out.println("""
                
                +++++++++++++++++++++++++
                WELCOME TO MERCADO LIBRE
                +++++++++++++++++++++++++
                
                  Select an option
                    [1] Manage products
                    [2] Manage deliveries
                
                """);
        String optionTemp = sc.nextLine();
        int option = Integer.parseInt(optionTemp);
        switch (option){
            case 1 :
                break;
            case 2 :
                break;
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
