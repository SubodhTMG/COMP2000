package Model.Customer;

public class CustomerModel {

    //region Variables
    String scannedStock;
    String cardPayment;
    String cashPayment;
    //endregion

    //region Get/Set Methods to Stored User Scanned Items
    public String getScannedStockStock(){
        return scannedStock;
    }

    public void setScannedStock(String stocks){
        this.scannedStock = stocks;
    }
    //endregion
}
