package Model.Admin.Observers;

public interface IOrders {
    void update(int barcode, String stockName, int quantity, double price);

    //region Get/Set variable and methods to store Item Details
    int getBarcode();
    String getStockName();
    int getStockQuantity();
    double getStockPrice();

    void setBarcode(int barcode);
    void setStockName(String stockName);
    void setQuantity(int quantity);
    void setStockPrice(double price);
    //endregion
}
