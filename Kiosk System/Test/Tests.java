import Controller.Observable.StockDatabaseSystem;
import Controller.Payment.Card;
import Controller.Payment.Cash;
import Controller.Payment.CustomerController;
import Controller.StockDatabaseController;
import Model.Admin.Observers.IOrders;
import Model.Admin.Observers.Orders;
import Admin.Admin;

import Customer.Customer;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Tests {



    Customer customer;
    CustomerController customerController;
    StockDatabaseSystem stockDatabaseSystem;
    StockDatabaseController stockDatabaseController;
    Admin admin = new Admin("");
    IOrders iOrders;
    Orders Orders;
    Cash cash;
    Card card;

    @Before
    public void SetUp(){
        System.out.println("");
        stockDatabaseSystem = new StockDatabaseSystem();
        stockDatabaseController = new StockDatabaseController(stockDatabaseSystem, admin);
        iOrders = new Orders(stockDatabaseSystem);
        cash = new Cash(customer, customerController, stockDatabaseSystem, Orders);
        card = new Card(customer, Orders);

    }

    @Test
    public void testAddStockItemName(){
        iOrders.setStockName("Coca Cola");
        stockDatabaseSystem.Add(iOrders);
        System.out.println(stockDatabaseSystem.stockItems.toString());
        stockDatabaseSystem.stockItems.toArray().toString();
    }

    @Test
    public void testEditStockItemBarcode(){
        System.out.println("Test Edit Barcode");
        int editBarcode = 15236;
        iOrders.setBarcode(84562);
        System.out.println(iOrders.getBarcode());
        iOrders.setBarcode(editBarcode);
        System.out.println(iOrders.getBarcode());
        assertEquals(editBarcode, iOrders.getBarcode());
    }

    @Test
    public void testEditStockItemName(){
        System.out.println("Test Edit Name");
        String editName = "Coco Cola";
        iOrders.setStockName("");
        System.out.println(iOrders.getStockName());
        iOrders.setStockName(editName);
        System.out.println(iOrders.getStockName());
        assertEquals(editName, iOrders.getStockName());
    }

    @Test
    public void testEditStockItemQuantity(){
        System.out.println("Testing Edit Quantity");
        String editName = "Chocolate";
        iOrders.setStockName("Milk");
        System.out.println(iOrders.getStockName());
        iOrders.setStockName(editName);
        System.out.println(iOrders.getStockName());
        assertEquals(editName, iOrders.getStockName());
    }

    @Test
    public void testEditStockItemPrice(){
        System.out.println("Testing Edit Price");
        double editPrice = 2.00;

        iOrders.setStockPrice(5.00);
        System.out.println(iOrders.getStockPrice());
        iOrders.setStockPrice(editPrice);
        System.out.println(iOrders.getStockPrice());
        assertEquals(editPrice + " ", iOrders.getStockPrice() + " ");
    }

    @Test
    public void testRemoveStockItem(){
        ArrayList<Orders> emptyArray = new ArrayList<>();

        iOrders.setStockName("Beans");
        stockDatabaseSystem.Add(iOrders);
        System.out.println(stockDatabaseSystem.stockItems.toString());;
        stockDatabaseSystem.stockItems.isEmpty();
    }

    @Test
    public void testSendUpdateMethod(){
        int barcode = 12;
        String  name = "Beans";
        int quantity = 100;
        double price = 2.00;

        stockDatabaseSystem.setValues(barcode, name, quantity, price);
        stockDatabaseSystem.sendUpdate();
    }

    @Test
    public void testRestockItem(){
        int RefillQuantity = 25;
        iOrders.setQuantity(35);
        if(iOrders.getStockQuantity() < 30){
            iOrders.setQuantity(RefillQuantity);
        }
        assertEquals(RefillQuantity, iOrders.getStockQuantity());
    }

    @Test
    public void testCreditCardDetails(){
        int bankNumber = 1234;
        String name = "John";
        card.setBankNumber(1234);
        card.setBankName("John");
        if(bankNumber == card.getBankNumber() && name.equals(card.getBankName())){
            System.out.println("Details are correct!");
        }
        assertEquals(bankNumber, card.getBankNumber());
        assertEquals(name, card.getBankName());
    }

    @Test
    public void testSetCashAmountGiven(){
        double cashAmount = 2.00;
        cash.setCashAmount(cashAmount);
        assertEquals(cashAmount + "", cash.getCashAmount() + "");
    }

    @Test
    public void testGetCashAmountReturned(){
        double cashAmount = 2.00;
        double getcashAmount;
        cash.setCashAmount(cashAmount);
        getcashAmount = cash.getCashAmount();
        assertEquals(cashAmount + "", getcashAmount + "");
    }

    @Test
    public void testSetBarcodeMethod(){
        System.out.println("Testing Set Barcode");
        int testBarcode = 12345;
        iOrders.setBarcode(12345);
        assertEquals(testBarcode, iOrders.getBarcode());
        System.out.println(testBarcode + " " + iOrders.getBarcode());
    }

    @Test
    public void testSetNameMethod(){
        System.out.println("Testing Set Name");
        String testName = "Beans";
        iOrders.setStockName("Beans");
        assertEquals(testName, iOrders.getStockName());
        System.out.println(testName + " " + iOrders.getStockName());
    }

    @Test
    public void testSetQuantityMethod(){
        System.out.println("Testing Set Quantity");
        int testQuantity = 100;
        iOrders.setQuantity(100);
        assertEquals(testQuantity, iOrders.getStockQuantity());
        System.out.println(testQuantity + " " + iOrders.getStockQuantity());
    }

    @Test
    public void testSetPriceMethod(){
        System.out.println("Testing Set Price");
        double testPrice = 2.29;
        iOrders.setStockPrice(2.29);
        assertEquals(testPrice + " ", iOrders.getStockPrice() + " ");
        System.out.println(testPrice + " " + iOrders.getStockQuantity());
    }
}
