package Controller.Admin;

import Controller.Observable.StockDatabaseSystem;
import Controller.StockDatabaseController;
import Model.Admin.AdminModel;
import Model.Admin.IAdminUsers;
import Model.Admin.Observers.Orders;
import Admin.Admin;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdminUsersController implements IAdminUsers {

    private AdminModel adminModel;
    private Orders Orders;
    private Admin admin;
    private StockDatabaseSystem stockDatabaseSystem;
    private StockDatabaseController stockDatabaseController;


    boolean trigger = false;
    public String Name;
    public String Password;
    public String filepath = "Data\\Logins.txt";
    public String separator = "\\|";
    int Quantity;


    public AdminUsersController(Admin av, Orders som, AdminModel am, StockDatabaseSystem sds, StockDatabaseController sdc){
        admin = av;
        Orders = som;
        adminModel = am;
        stockDatabaseSystem = sds;
        stockDatabaseController = sdc;
    }

    public void initAdminUsersController(){

        admin.getBtnLogin().addActionListener(e -> Login());
        admin.btnDelivery.addActionListener(e -> OrderStock());
    }


    @Override
    public void Login() {

        Name = admin.getTxtUsername().getText();
        Password = admin.getTxtPassword().getText();


        adminModel.setAdminUsername(Name);
        adminModel.setAdminPassword(Password);

        try{
            File file = new File(filepath);

            Scanner scanner = new Scanner(file);


            while (scanner.hasNextLine()) {
                String tableRow = scanner.nextLine();

                String[] LoginDe = tableRow.split(separator);


                Name = LoginDe[0];
                Password = LoginDe[1];


                AccessStock();
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void AccessStock() {

        if(admin.getTxtUsername().getText().equals(Name) && admin.getTxtPassword().getText().equals(Password)){
            ReplenishStockWarning(0);
            admin.getCardLayout().show(admin.AdminPanel, "AdminDatabasePage");
            System.out.println("Success: Correct Admin Details Provided!");
        }
        else{
            admin.getLblDetailsIncorrect().setText("Error: Incorrect Admin Details!");
        }
    }

    @Override
    public void OrderStock() {

        for (int i = 0; i < stockDatabaseSystem.stockItems.toArray().length; i++)
        {

            Quantity = stockDatabaseSystem.stockItems.get(i).getStockQuantity();

            if(Quantity < 50){
                JOptionPane.showMessageDialog(null, "Replenishing Stock On All Low Stock Items","Replenishing Stock", JOptionPane.INFORMATION_MESSAGE);
                trigger = true;
                ReplenishStockOnDelivery();
            }
        }

        if(Quantity >= 50){
            JOptionPane.showMessageDialog(null, "No Stock Needs To Be Replenished!","Replenishing Stock", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void ReplenishStockWarning(int Quantity) {
        //For loop which runs based on the amount of stock in the StockItemslist.txt
        for (int i = 0; i < stockDatabaseSystem.stockItems.size(); i++)
        {
            //Store the Quantity inside of the selected index item into Quantity variable
            Quantity = stockDatabaseSystem.stockItems.get(i).getStockQuantity();

            //IF Quantity is > than 50, inform the Admin when the log in
            if(Quantity < 50){
                JOptionPane.showMessageDialog(null, "Stock is low on these item(s): " + stockDatabaseSystem.stockItems.get(i).getStockName(),"Stock Supply Warning!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void ReplenishStockOnDelivery() {
        JOptionPane.showMessageDialog(null, "Stock Delivery is on the way!","Replenishing Stock", JOptionPane.INFORMATION_MESSAGE);
        try {
            Thread.sleep(10000);
            if(trigger){
                for (int i = 0; i < stockDatabaseSystem.stockItems.toArray().length; i++)
                {
                    Quantity = stockDatabaseSystem.stockItems.get(i).getStockQuantity();
                    if(Quantity < 50){
                        JOptionPane.showMessageDialog(null, "Stock Delivery Has Been Made For: " + stockDatabaseSystem.stockItems.get(i).getStockName(),"Replenishing Stock", JOptionPane.INFORMATION_MESSAGE);
                        stockDatabaseSystem.stockItems.get(i).setQuantity(100);
                        stockDatabaseController.saveKioskStock();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //endregion
}
