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

    //region Objects and Variables
    //Objects
    Customer ckv;
    Orders som;
    Bank details = new Bank();

    //Variables
    String filepath = "resources\\BankDetails.txt";
    String separator = "\\|";
    //endregion

    public Card(Customer ckv, Orders som){
        this.ckv = ckv;
        this.som = som;
    }

    public void initCPayment(){
        ckv.btnCardSubmit.addActionListener(e -> Payment());
    }

    //region Payment and BankVerification Methods
    @Override
    public void Payment(){
        try{
            File file = new File(filepath);

            Scanner scanner = new Scanner(file);

            //Scan through the BankDetails.txt file
            while (scanner.hasNextLine()) {
                String tableRow = scanner.nextLine();

                String[] bankDetails = tableRow.split(separator);

                //Store the Bank Name, Customer Name, and Bank Number into the set methods inside the bank class
                details.setBankName(bankDetails[0]);
                details.setCustomerName(bankDetails[1]);
                details.setBankNumber(Integer.parseInt(bankDetails[2]));

                //IF the text boxes of Card Name and Card Pin are empty, warn the user
                if(ckv.txtCardName.getText().isEmpty() || ckv.txtCardPin.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Make sure all details are provided!","Warning", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                //Run the Bank Verification Method
                BankVerification();
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void BankVerification(){
        try{
            //IF the inserted values from all text boxes match a row in the BankDetails.txt, continue
            if(ckv.getCbBank().getSelectedItem().equals(details.getBankName())
                    && ckv.getTxtCardName().getText().equals(details.getCustomerName())
                    && ckv.getTxtCardNumber().getText().equals(Integer.toString(details.getBankNumber()))){

                //set bool to true
                ChoosePayment.getInstance().setCardOption(true);
                if(ChoosePayment.getInstance().getCardOption()){
                    //Change panel to the Verification Panel
                    ckv.getCardLayout().show(ckv.MainKioskPanel, "VerificationPanel");

                    //Change label text to selected bank
                    ckv.lblBankNameVerification.setText(ckv.getCbBank().getSelectedItem().toString() + " Verification");

                    //Sleep
                    Thread.sleep(1500);

                    //Inform user that payment is valid
                    ckv.lblVerification.setText("Payment Has Been Verified");
                }
            }else{
                //Set label to the bank chosen
                ckv.lblInvalidPayment.setText(ckv.cbBank.getSelectedItem().toString() + ": Payment is invalid");
            }
        }catch(Exception e){
            System.out.print(e);
        }
    }
    //endregion
}
