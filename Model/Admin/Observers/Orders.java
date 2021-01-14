package Model.Admin.Observers;

import Controller.Observable.IStockDatabaseSystem;

public class Orders implements IOrders {

    //region Variables
    public int Barcode;

    public String Name;

    public int Quantity;

    public double Price;
    //endregion

    public Orders(IStockDatabaseSystem stockDatabase){
        stockDatabase.Add(this);
        stockDatabase.Remove(this);
    }

    @Override
    public void update(int barcode, String stockName, int quantity, double price) {
        setBarcode(barcode);
        setStockName(stockName);
        setQuantity(quantity);
        setStockPrice(price);
    }

    //region Get/Set Methods
    @Override
    public int getBarcode() {
        return Barcode;
    }

    @Override
    public String getStockName() {
        return Name;
    }

    @Override
    public int getStockQuantity() {
        return Quantity;
    }

    @Override
    public double getStockPrice() {
        return Price;
    }

    @Override
    public void setBarcode(int barcode) {
        Barcode = barcode;
    }

    @Override
    public void setStockName(String stockName) {
        Name = stockName;
    }

    @Override
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    @Override
    public void setStockPrice(double price) {
        Price = price;
    }
    //endregion
    //endregion
}
