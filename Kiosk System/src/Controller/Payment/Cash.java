package Controller.Payment;

import Controller.Observable.StockDatabaseSystem;
import Model.ChoosePayment;
import Model.Customer.IPaymentMethod;
import Model.Admin.Observers.Orders;
import Customer.Customer;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Cash implements IPaymentMethod {

    //region Objects and Variables
    //region Class Objects
    Customer skv;
    CustomerController cc;
    StockDatabaseSystem sdc;
    Orders som;



    Double CashAmount;
    Double totalCashRequired;
    Double Change;

    NumberFormat formatter = new DecimalFormat("#0.00");


    public Cash(Customer ckv, CustomerController cc, StockDatabaseSystem sdc, Orders som){
        skv = ckv;
        this.cc = cc;
        this.sdc = sdc;
        this.som = som;
    }

    public void initCashPayment(){

        skv.btnPay.addActionListener(e -> Payment());
    }

    //region Payment Method
    @Override
    public void Payment(){

        if(skv.txtCashAmount.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No Cash Amount Has Been Entered!","Warning", JOptionPane.INFORMATION_MESSAGE);
        }
        else {


            totalCashRequired = cc.getTotalItemPrices();


            CashAmount = Double.parseDouble(skv.getTxtCashAmount().getText());


            Change = totalCashRequired - CashAmount;
        }

        if(Change <= 0){

            ChoosePayment.getInstance().setCashOption(true);


            setCashAmount(Change);

            skv.getCardLayout().show(skv.MainKioskPanel, "PaymentComplete");

            skv.txtCashAmount.setText("");
        }
        else if(Change > 0.01) {

            JOptionPane.showMessageDialog(
                    null,
                    "Product still not paid, You still owe: " + formatter.format(Change),
                    "Replenishing Stock", JOptionPane.INFORMATION_MESSAGE);
        }
    }



    public Double getCashAmount(){
        return CashAmount;
    }

    public void setCashAmount(Double cash){
        this.CashAmount = cash;
    }

}
