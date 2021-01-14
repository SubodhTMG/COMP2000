package Model;

public class ChoosePayment {
    private static ChoosePayment instance;


    private boolean cardOption = false;
    private boolean cashOption = false;

    private void ChoosePaymentSingle(){}

    public static ChoosePayment getInstance(){
        if(instance == null){
            instance = new ChoosePayment();
        }
        return instance;
    }


    public boolean getCardOption(){
        return cardOption;
    }

    public void setCardOption(boolean cardOption){
        this.cardOption = cardOption;
    }

    public boolean getCashOption(){
        return cashOption;
    }

    public void setCashOption(boolean cashOption){
        this.cashOption = cashOption;
    }

}
