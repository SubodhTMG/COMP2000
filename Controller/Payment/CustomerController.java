package Controller.Payment;

import Controller.Observable.StockDatabaseSystem;
import Controller.StockDatabaseController;
import Model.Admin.Observers.Orders;
import Customer.Customer;
import Model.Customer.CustomerModel;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CustomerController{

    //region Objects and Variables
    //region Class Objects
    StockDatabaseController sdc;
    StockDatabaseSystem sds;
    Orders som;
    CustomerModel cm;
    Customer ckv;
    //endregion

    //region Variables
    String userBarcodeInput;
    String stockName;
    int Quantity;
    double stockPrice;
    double totalItemPrices;

    //Create default list model called dm
    DefaultListModel<String> dm = new DefaultListModel<String>();

    //Create a number formatter
    NumberFormat formatter = new DecimalFormat("#0.00");
    //endregion
    //endregion

    public CustomerController(StockDatabaseController sdc, StockDatabaseSystem sds, Orders som, CustomerModel cm, Customer ckv){
        this.sdc = sdc;
        this.sds = sds;
        this.som = som;
        this.cm = cm;
        this.ckv = ckv;

        //Run these methods
        initCustomerController();
        StockPayment();
    }

    public void  initCustomerController(){
        //Button presses call methods inside CustomerController Class
        ckv.getBtnKioskAdd().addActionListener(e -> ScanStock());
    }

    //region ScanStock, StockPayment, and UpdateStock Methods
    public void ScanStock(){
        //setScannedStock to the barcode value inserted by the user
        cm.setScannedStock(ckv.getTxtBarcodeScan().getText());

        //Store the scanned barcode value into userBarcodeInput
        userBarcodeInput = cm.getScannedStockStock();

        //set the model for the basket list to dm
        ckv.lstBasket.setModel(dm);
        try{
            //Run a for loop based on the size of the stockItem array in the StockDatabaseController Class
            for (int i = 0; i < sds.stockItems.size(); i++)
            {
                //IF the input barcode is equal to a barcode stored in the stockItem array, continue
                if(userBarcodeInput.equals(Integer.toString(sds.stockItems.get(i).getBarcode()))){
                    //retrieve the quantity from selected stock item and - 1
                    sds.stockItems.get(i).setQuantity(sds.stockItems.get(i).getStockQuantity() - 1);
                    //IF selected stock item has a Quantity of < 0, do not add to basket
                    if(sds.stockItems.get(i).getStockQuantity() < 0){
                        //Inform the user that we can't add item to basket
                        JOptionPane.showMessageDialog(null, sds.stockItems.get(i).getStockName() + " is out of Stock!");
                        break;
                    }
                    //Store Item Name and Price into stockName and stockPrice
                    stockName = sds.stockItems.get(i).getStockName();
                    stockPrice = sds.stockItems.get(i).getStockPrice();

                    //Increment Quantity
                    Quantity += 1;

                    //totalItemPrices stores the price of the Item, we then calculate the total with totalItemPrices * Quantity
                    totalItemPrices = totalItemPrices + stockPrice;

                    //Add the item and item price to a list
                    dm.addElement("Item: " + stockName + "  " + "Price: £" + stockPrice);
                    ckv.txtTotalPrice.setText("£" + formatter.format(totalItemPrices));
                }
            }
            //store the total price of all customer items
            setTotalItemPrices(totalItemPrices);
            ckv.lblPaymentDue.setText("You Must Pay: £" + formatter.format(totalItemPrices));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void StockPayment(){
        //Check for user button press and run selcted method
        ckv.getBtnCardPurchases().addActionListener(e -> CardPaymentOption());
        ckv.getBtnCashPurchase().addActionListener(e -> CashPaymentOption());
    }

    //region Card/Cash Methods
    public void CardPaymentOption(){
        //Open the Card Payment Page
        ckv.getCardLayout().show(ckv.MainKioskPanel, "CardPaymentPanel");
    }

    public void CashPaymentOption(){
        //Open the Cash Payment Page
        ckv.getCardLayout().show(ckv.MainKioskPanel, "CashPaymentPanel");
    }
    //endregion

    //region Get/Sets for Total Price of Customer Items
    public Double getTotalItemPrices(){
        return totalItemPrices;
    }

    public void setTotalItemPrices(double totPrice){
        totalItemPrices = totPrice;
    }

    //endregion
}
