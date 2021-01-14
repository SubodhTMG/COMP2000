package Controller.Payment;

import Model.ChoosePayment;
import Model.Customer.Bank;
import Model.Customer.IPaymentMethod;
import Model.Admin.Observers.Orders;
import Customer.Customer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Card extends Bank implements IPaymentMethod {


    Customer ckv;
    Orders som;
    Bank details = new Bank();


    String filepath = "resources\\BankDetails.txt";
    String separator = "\\|";


    public Card(Customer ckv, Orders som){
        this.ckv = ckv;
        this.som = som;
    }

    public void initCPayment(){
        ckv.btnCardSubmit.addActionListener(e -> Payment());
    }


    @Override
    public void Payment(){
        try{
            File file = new File(filepath);

            Scanner scanner = new Scanner(file);


            while (scanner.hasNextLine()) {
                String tableRow = scanner.nextLine();

                String[] bankDetails = tableRow.split(separator);


                details.setBankName(bankDetails[0]);
                details.setCustomerName(bankDetails[1]);
                details.setBankNumber(Integer.parseInt(bankDetails[2]));

                if(ckv.txtCardName.getText().isEmpty() || ckv.txtCardPin.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Make sure all details are provided!","Warning", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                BankVerification();
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void BankVerification(){
        try{

            if(ckv.getCbBank().getSelectedItem().equals(details.getBankName())
                    && ckv.getTxtCardName().getText().equals(details.getCustomerName())
                    && ckv.getTxtCardNumber().getText().equals(Integer.toString(details.getBankNumber()))){


                ChoosePayment.getInstance().setCardOption(true);
                if(ChoosePayment.getInstance().getCardOption()){

                    ckv.getCardLayout().show(ckv.MainKioskPanel, "VerificationPanel");

                    ckv.lblBankNameVerification.setText(ckv.getCbBank().getSelectedItem().toString() + " Verification");

                    Thread.sleep(1500);


                    ckv.lblVerification.setText("Payment Has Been Verified");
                }
            }else{

                ckv.lblInvalidPayment.setText(ckv.cbBank.getSelectedItem().toString() + ": Payment is invalid");
            }
        }catch(Exception e){
            System.out.print(e);
        }
    }

}
