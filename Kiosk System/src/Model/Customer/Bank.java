package Model.Customer;

public class Bank{



    String bankName;
    String customerName;
    int bankNumber;

    public String getBankName(){
        return bankName;
    }

    public void setBankName(String AdminUsername){
        this.bankName = AdminUsername;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public int getBankNumber(){
        return bankNumber;
    }

    public void setBankNumber(int bankNumber){
        this.bankNumber = bankNumber;
    }

}
