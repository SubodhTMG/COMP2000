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

    //region Class Objects and Variables
    //Class Objects
    private AdminModel adminModel;
    private Orders stockOrders;
    private Admin admin;
    private StockDatabaseSystem stockDatabaseSystem;
    private StockDatabaseController stockDatabaseController;

    //Variables
    boolean trigger = false;
    public String Name;
    public String Password;
    public String filepath = "resources\\AdminUserLogins.txt";
    public String separator = "\\|";
    int Quantity;
    //endregion

    public AdminUsersController(Admin av, Orders som, AdminModel am, StockDatabaseSystem sds, StockDatabaseController sdc){
        admin = av;
        stockOrders = som;
        adminModel = am;
        stockDatabaseSystem = sds;
        stockDatabaseController = sdc;
    }

    public void initAdminUsersController(){
        //Detect user button press and run selected methods
        admin.getBtnLogin().addActionListener(e -> Login());
        admin.btnDelivery.addActionListener(e -> OrderStock());
    }

    //region Login, AccessStock, OrderStock, ReplenishStockWarning, ReplenishStockOnDelivery Methods
    @Override
    public void Login() {
        //Get value from txtUsername and txtPassword and store them in Name and Password variables
        Name = admin.getTxtUsername().getText();
        Password = admin.getTxtPassword().getText();

        //Store Name and Password into setAdminUsername and SetAdminPassword
        adminModel.setAdminUsername(Name);
        adminModel.setAdminPassword(Password);

        try{
            File file = new File(filepath);

            Scanner scanner = new Scanner(file);

            //Run a loop to check if Username and Password are valid in the AdminUserLogins text file
            while (scanner.hasNextLine()) {
                String tableRow = scanner.nextLine();

                String[] LoginDe = tableRow.split(separator);

                //Store index 0 into Name String and store index 1 into Password string
                Name = LoginDe[0];
                Password = LoginDe[1];

                //Run Method
                AccessStock();
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void AccessStock() {
        //If the Username and Password entered by the user are equal to the Name & Password variables, let them enter
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
        //For loop which runs based on the amount of stock in the StockItemslist.txt
        for (int i = 0; i < stockDatabaseSystem.stockItems.toArray().length; i++)
        {
            //Store the Quantity from the txt file into the Quantity variable
            Quantity = stockDatabaseSystem.stockItems.get(i).getStockQuantity();
            //IF the quantity is < 50, Inform the Admin that stock is being replenished and run ReplenishStockDelivery Method
            if(Quantity < 50){
                JOptionPane.showMessageDialog(null, "Replenishing Stock On All Low Stock Items","Replenishing Stock", JOptionPane.INFORMATION_MESSAGE);
                trigger = true;
                ReplenishStockOnDelivery();
            }
        }
        //IF Quantity is > than 50, do not replenish stock
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
