package Model;

import Controller.Observable.StockDatabaseSystem;
import Model.Admin.Observers.IOrders;
import Model.Admin.Observers.Orders;
import Admin.Admin;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DisplayStockListData {
    Admin admin;
    StockDatabaseSystem stockDatabaseSystem;
    Orders stockOrders;

    int selectedEditIndex;
    int selectedViewIndex;

    public DisplayStockListData(Admin av, StockDatabaseSystem sds, Orders som){
        admin = av;
        stockDatabaseSystem = sds;
        stockOrders = som;
    }

    //Method used to listen to used selected index input
    public void SetUpLstData(){
        admin.lstStockEditDisplay.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!admin.lstStockEditDisplay.isSelectionEmpty()){
                    selectedEditIndex = admin.lstStockEditDisplay.getMinSelectionIndex();
                    DisplayData();
                }
            }
        });

        admin.lstDisplayStock.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!admin.lstDisplayStock.isSelectionEmpty()){
                    selectedViewIndex = admin.lstDisplayStock.getMinSelectionIndex();
                    DisplayViewData();
                }
            }
        });
    }

    //region Display selected index data from JLists properly
    public void DisplayData(){
        //allStockItems array Gets selected index item from lstStockEditDisplay and stores it in StockOrderModel class object
        IOrders currentSelectedEditItem = stockDatabaseSystem.stockItems.get(selectedEditIndex);

        //Temp variables to store correct selected index data
        int tempEditBarcodeNum = currentSelectedEditItem.getBarcode();
        String tempEditStockName = currentSelectedEditItem.getStockName();
        int tempEditStockQuantity = currentSelectedEditItem.getStockQuantity();
        double tempEditStockPrice = currentSelectedEditItem.getStockPrice();

        // Display the temp variable data in the correct text boxes
        admin.txtEditBarcode.setText(Integer.toString(tempEditBarcodeNum));
        admin.txtEditName.setText(tempEditStockName);
        admin.txtEditQuantity.setText(Integer.toString(tempEditStockQuantity));
        admin.txtEditPrice.setText(Double.toString(tempEditStockPrice));
    }

    public void DisplayViewData(){
        //allStockItems array Gets selected index item from lstDisplayStock and stores it in StockOrderModel class object
        IOrders currentViewSelectedItem = stockDatabaseSystem.stockItems.get(selectedViewIndex);

        //Temp variables to store correct selected index data
        int tempViewBarcodeNum = currentViewSelectedItem.getBarcode();
        String tempViewStockName = currentViewSelectedItem.getStockName();
        int tempViewStockQuantity = currentViewSelectedItem.getStockQuantity();
        double tempViewStockPrice = currentViewSelectedItem.getStockPrice();

        //Display the temp variable data in the correct text boxes
        admin.txtViewBarcode.setText(Integer.toString(tempViewBarcodeNum));
        admin.txtViewName.setText(tempViewStockName);
        admin.txtViewQuantity.setText(Integer.toString(tempViewStockQuantity));
        admin.txtViewPrice.setText(Double.toString(tempViewStockPrice));
    }
    //endregion
}
