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


    IPaymentMethod pm;
    CustomerController cc;
    Customer ckv;
    StockDatabaseController ssd;
    Card cardP;
    Cash cashP;

    String CompanyName;
    String Date;
    String PaymentOption;
    String Change;
    String TotalCost;

    NumberFormat formatter = new DecimalFormat("#0.00");
    DefaultListModel<String> displayElements = new DefaultListModel<String>();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();

    public Receipt(IPaymentMethod ipm, CustomerController cc, Customer ckv, StockDatabaseController ssd, Card cardPay, Cash cashPay) {
        this.pm = ipm;
        this.cc = cc;
        this.ckv = ckv;
        this.ssd = ssd;
        cardP = cardPay;
        cashP = cashPay;
    }

    public void initReceipt(){

        ckv.btnReceipt.addActionListener(e -> Payment());
        ckv.btnReturn.addActionListener(e -> ResetData());
    }


    public void Payment() {

        VerifyReceiptData();
    }

    public void PrintoutData() {

        ckv.lblCompanyName.setText(getCompanyName());
        ckv.lblCurrentDate.setText(getDate());
        ckv.lblPaymentChoice.setText(getPaymentOption());
        ckv.lblCashChange.setText(getChange());
        ckv.lblTotalCost.setText(getTotalCost());
    }

    public void VerifyReceiptData() {

        Thread verifyData = new Thread();

        try {

            verifyData.start();

            ckv.lstDisplayBoughtItems.setModel(displayElements);


            setCompanyName("Comp Kiosk");

            setDate(dtf.format(now));


            if (ChoosePayment.getInstance().getCardOption()) {
                setPaymentOption("Credit Card");
                setChange("£0.00");


                ssd.saveKioskStock();
            } else if (ChoosePayment.getInstance().getCashOption()) {
                setPaymentOption("Cash");
                setChange("£" + formatter.format(cashP.getCashAmount()));


                ssd.saveKioskStock();
            }

            setTotalCost("£" + formatter.format(cc.getTotalItemPrices()));


            for(int i = 0; ckv.lstBasket.isSelectionEmpty(); i++){
                displayElements.addElement(cc.dm.elementAt(i));
            }
        } catch (Exception e) {
            System.out.print(e);
        }


        PrintoutData();


        ckv.getCardLayout().show(ckv.MainKioskPanel, "ReceiptPanel");
    }

    public void ResetData(){

        ChoosePayment.getInstance().setCardOption(false);
        ChoosePayment.getInstance().setCashOption(false);


        ckv.lblInvalidPayment.setText("");
        ckv.txtCardPin.setText("");
        ckv.txtCardName.setText("");
        ckv.txtTotalPrice.setText("");


        cc.setTotalItemPrices(0);
        cc.dm.removeAllElements();


        displayElements.removeAllElements();


        ckv.cardLayout.show(ckv.MainKioskPanel, "CustomerKioskPanel");
    }

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


}
