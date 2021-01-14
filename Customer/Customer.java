package Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer {

    //region CustomerKioskView GUI JAttributes
    public JPanel MainKioskPanel;
    public JPanel KioskPanel;
    public JPanel CashPaymentPanel;
    public JPanel CardPaymentPanel;
    private JPanel VerificationScreen;
    private JPanel PaymentComplete;
    public JPanel ReceiptPanel;

    private JButton btnAddItem;
    public JButton btnPayNow;
    public JButton btnCash;
    public JButton btnReturn;
    public JButton btnPay;
    public JButton btnCardSubmit;
    public JButton btnReceipt;
    private JButton btnClose;
    private JButton btnPaymentPage;

    public JTextField txtCashAmount;
    public JTextField txtCardName;
    public JTextField txtCardPin;
    public JTextField txtBarcodeScanner;
    public JTextField txtTotalPrice;

    public JLabel lblCompanyName;
    public JLabel lblCurrentDate;
    public JLabel lblPaymentChoice;
    public JLabel lblCashChange;
    public JLabel lblTotalCost;
    public JLabel lblVerification;
    public JLabel lblBankNameVerification;
    public JLabel lblPaymentDue;
    public JLabel lblInvalidPayment;

    public JList lstDisplayBoughtItems;
    public JList lstBasket;
    public JComboBox cbBank;
    //endregion

    public JFrame kioskFrame;
    public CardLayout cardLayout = new CardLayout();

    public Customer(String CustomerMenu){
        //Set our card layout and add our Panels to it!
        MainKioskPanel.setLayout(cardLayout);
        MainKioskPanel.add(KioskPanel, "CustomerKioskPanel");
        MainKioskPanel.add(CardPaymentPanel, "CardPaymentPanel");
        MainKioskPanel.add(CashPaymentPanel, "CashPaymentPanel");
        MainKioskPanel.add(VerificationScreen, "VerificationPanel");
        MainKioskPanel.add(PaymentComplete, "PaymentComplete");
        MainKioskPanel.add(ReceiptPanel, "ReceiptPanel");

        cardLayout.show(MainKioskPanel, "1");

        //Create our form layout
        kioskFrame = new JFrame(CustomerMenu);
        kioskFrame.setResizable(false);
        kioskFrame.add(MainKioskPanel);
        kioskFrame.setSize(500, 500);
        kioskFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        kioskFrame.setLocationRelativeTo(null);
        kioskFrame.pack();
        kioskFrame.setVisible(true);

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        btnPaymentPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(MainKioskPanel, "PaymentComplete");
            }
        });
    }

    //region Get/Set Methods
    public CardLayout getCardLayout(){
        return cardLayout;
    }

    //region TextField Get/Sets
    public JTextField getTxtBarcodeScan(){
        return txtBarcodeScanner;
    }

    public JTextField getTxtCashAmount(){
        return txtCashAmount;
    }

    public JComboBox getCbBank(){
        return cbBank;
    }

    public JTextField getTxtCardName(){
        return txtCardName;
    }

    public JTextField getTxtCardNumber(){
        return txtCardPin;
    }
    //endregion

    //region JButtons Get/Sets
    public JButton getBtnKioskAdd(){
        return btnAddItem;
    }

    public JButton getBtnCardPurchases(){
        return btnPayNow;
    }

    public JButton getBtnCashPurchase(){
        return btnCash;
    }

    //endregion
    //endregion
}
