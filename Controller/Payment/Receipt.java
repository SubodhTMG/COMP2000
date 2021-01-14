package Controller.Payment;

import Controller.StockDatabaseController;
import Model.ChoosePayment;
import Model.Customer.IPaymentMethod;
import Customer.Customer;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Receipt implements IPaymentMethod {

    //region Objects and Attributes
    IPaymentMethod pm;
    CustomerController cc;
    Customer ckv;
    StockDatabaseController ssd;
    Card cardP;
    Cash cashP;
    //endregion

    //region String Variables
    String CompanyName;
    String Date;
    String PaymentOption;
    String Change;
    String TotalCost;

    NumberFormat formatter = new DecimalFormat("#0.00");
    DefaultListModel<String> displayElements = new DefaultListModel<String>();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();
    //endregion

    //Extends classes to communicate with Receipt
    public Receipt(IPaymentMethod ipm, CustomerController cc, Customer ckv, StockDatabaseController ssd, Card cardPay, Cash cashPay) {
        this.pm = ipm;
        this.cc = cc;
        this.ckv = ckv;
        this.ssd = ssd;
        cardP = cardPay;
        cashP = cashPay;
    }

    public void initReceipt(){
        //IF Receipt or Return button is pressed, run selected Method
        ckv.btnReceipt.addActionListener(e -> Payment());
        ckv.btnReturn.addActionListener(e -> ResetData());
    }

    //region Payment, PrintoutData, VerifyReceiptData, & ResetData Methods
    public void Payment() {
        //Run VerifyReceiptData Method
        VerifyReceiptData();
    }

    public void PrintoutData() {
        //Set labels on the Receipt page to selected get methods
        ckv.lblCompanyName.setText(getCompanyName());
        ckv.lblCurrentDate.setText(getDate());
        ckv.lblPaymentChoice.setText(getPaymentOption());
        ckv.lblCashChange.setText(getChange());
        ckv.lblTotalCost.setText(getTotalCost());
    }

    public void VerifyReceiptData() {
        //Create a new Thread called verifyData
        Thread verifyData = new Thread();

        try {
            //Start Thread
            verifyData.start();

            //store the customers purchased items into lstDisplayBoughtItems
            ckv.lstDisplayBoughtItems.setModel(displayElements);

            //Set the CompanyName label
            setCompanyName("Caleb's Shopping Kiosk");

            //set the date label to current date
            setDate(dtf.format(now));

            //If and Else statements to determine the choice of payment the customer made
            if (ChoosePayment.getInstance().getCardOption()) {
                setPaymentOption("Credit Card");
                setChange("£0.00");

                //If the payment is successful we can remove the purchased stock
                ssd.saveKioskStock();
            } else if (ChoosePayment.getInstance().getCashOption()) {
                setPaymentOption("Cash");
                setChange("£" + formatter.format(cashP.getCashAmount()));

                //If the payment is successful we can remove the purchased stock
                ssd.saveKioskStock();
            }
            //retrieve the Total cost of the Customers Items and set them into the Total Cost label
            setTotalCost("£" + formatter.format(cc.getTotalItemPrices()));

            //Run a for loop to read through the customers shopping basket and displays its contents into the receipt list
            for(int i = 0; ckv.lstBasket.isSelectionEmpty(); i++){
                displayElements.addElement(cc.dm.elementAt(i));
            }
        } catch (Exception e) {
            System.out.print(e);
        }

        //Print the information
        PrintoutData();

        //Resetting all data!
        ckv.getCardLayout().show(ckv.MainKioskPanel, "ReceiptPanel");
    }

    public void ResetData(){
        //Card/Cash Payment booleans set to false
        ChoosePayment.getInstance().setCardOption(false);
        ChoosePayment.getInstance().setCashOption(false);

        //Set Customer Kiosk View values to default
        ckv.lblInvalidPayment.setText("");
        ckv.txtCardPin.setText("");
        ckv.txtCardName.setText("");
        ckv.txtTotalPrice.setText("");

        //Set Customer Controller values to default
        cc.setTotalItemPrices(0);
        cc.dm.removeAllElements();

        //Empty Customer Basket
        displayElements.removeAllElements();

        //Change page to Kiosk Panel
        ckv.cardLayout.show(ckv.MainKioskPanel, "CustomerKioskPanel");
    }
    //endregion

    //region Get/Set Methods
    public String getCompanyName(){
        return CompanyName;
    }

    public void setCompanyName(String companyName){
        this.CompanyName = companyName;
    }

    public String getDate(){
        return Date;
    }

    public void setDate(String date){
        this.Date = date;
    }

    public String getPaymentOption(){
        return PaymentOption;
    }

    public void setPaymentOption(String paymentOption){
        this.PaymentOption = paymentOption;
    }

    public String getChange(){
        return Change;
    }

    public void setChange(String change){
        this.Change = change;
    }

    public String getTotalCost(){
        return TotalCost;
    }

    public void setTotalCost(String totalCost){
        this.TotalCost = totalCost;
    }
    //endregion

}
