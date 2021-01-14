package Controller.Observable;

import Model.Admin.Observers.IOrders;
import java.util.ArrayList;

public class StockDatabaseSystem implements IStockDatabaseSystem {

    public final ArrayList<IOrders> stockItems = new ArrayList<IOrders>();

    private String adminNameInput;
    private int adminBarcodeInput;
    private int adminQuantity;
    private double adminPrice;


    public void setValues(int barcode, String stockName, int quantity, double price){
        adminBarcodeInput = barcode;
        adminNameInput = stockName;
        adminQuantity = quantity;
        adminPrice = price;

        sendUpdate();
    }

    public void sendUpdate(){

        for (IOrders stockItem : stockItems) {
            stockItem.update(adminBarcodeInput, adminNameInput, adminQuantity, adminPrice);
        }
    }


    @Override
    public void Add(IOrders stockOrdersModel) {
        stockItems.add(stockOrdersModel);
    }

    @Override
    public void Remove(IOrders stockOrdersModel) {
        stockItems.remove(stockOrdersModel);
    }

}

