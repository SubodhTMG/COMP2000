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


    StockDatabaseController sdc;
    StockDatabaseSystem sds;
    Orders som;
    CustomerModel cm;
    Customer ckv;


    String userBarcodeInput;
    String stockName;
    int Quantity;
    double stockPrice;
    double totalItemPrices;


    DefaultListModel<String> dm = new DefaultListModel<String>();

    NumberFormat formatter = new DecimalFormat("#0.00");


    public CustomerController(StockDatabaseController sdc, StockDatabaseSystem sds, Orders som, CustomerModel cm, Customer ckv){
        this.sdc = sdc;
        this.sds = sds;
        this.som = som;
        this.cm = cm;
        this.ckv = ckv;

        initCustomerController();
        StockPayment();
    }

    public void  initCustomerController(){

        ckv.getBtnKioskAdd().addActionListener(e -> ScanStock());
    }

    public void ScanStock(){

        cm.setScannedStock(ckv.getTxtBarcodeScan().getText());

        userBarcodeInput = cm.getScannedStockStock();


        ckv.lstBasket.setModel(dm);
        try{

            for (int i = 0; i < sds.stockItems.size(); i++)
            {

                if(userBarcodeInput.equals(Integer.toString(sds.stockItems.get(i).getBarcode()))){

                    sds.stockItems.get(i).setQuantity(sds.stockItems.get(i).getStockQuantity() - 1);

                    if(sds.stockItems.get(i).getStockQuantity() < 0){

                        JOptionPane.showMessageDialog(null, sds.stockItems.get(i).getStockName() + " is out of Stock!");
                        break;
                    }

                    stockName = sds.stockItems.get(i).getStockName();
                    stockPrice = sds.stockItems.get(i).getStockPrice();


                    Quantity += 1;


                    totalItemPrices = totalItemPrices + stockPrice;


                    dm.addElement("Item: " + stockName + "  " + "Price: £" + stockPrice);
                    ckv.txtTotalPrice.setText("£" + formatter.format(totalItemPrices));
                }
            }

            setTotalItemPrices(totalItemPrices);
            ckv.lblPaymentDue.setText("You Must Pay: £" + formatter.format(totalItemPrices));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void StockPayment(){

        ckv.getBtnCardPurchases().addActionListener(e -> CardPaymentOption());
        ckv.getBtnCashPurchase().addActionListener(e -> CashPaymentOption());
    }

    public void CardPaymentOption(){

        ckv.getCardLayout().show(ckv.MainKioskPanel, "CardPaymentPanel");
    }

    public void CashPaymentOption(){

        ckv.getCardLayout().show(ckv.MainKioskPanel, "CashPaymentPanel");
    }


    public Double getTotalItemPrices(){
        return totalItemPrices;
    }

    public void setTotalItemPrices(double totPrice){
        totalItemPrices = totPrice;
    }


}
