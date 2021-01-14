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


    public void DisplayData(){

        IOrders currentSelectedEditItem = stockDatabaseSystem.stockItems.get(selectedEditIndex);


        int tempEditBarcodeNum = currentSelectedEditItem.getBarcode();
        String tempEditStockName = currentSelectedEditItem.getStockName();
        int tempEditStockQuantity = currentSelectedEditItem.getStockQuantity();
        double tempEditStockPrice = currentSelectedEditItem.getStockPrice();


        admin.txtEditBarcode.setText(Integer.toString(tempEditBarcodeNum));
        admin.txtEditName.setText(tempEditStockName);
        admin.txtEditQuantity.setText(Integer.toString(tempEditStockQuantity));
        admin.txtEditPrice.setText(Double.toString(tempEditStockPrice));
    }

    public void DisplayViewData(){

        IOrders currentViewSelectedItem = stockDatabaseSystem.stockItems.get(selectedViewIndex);


        int tempViewBarcodeNum = currentViewSelectedItem.getBarcode();
        String tempViewStockName = currentViewSelectedItem.getStockName();
        int tempViewStockQuantity = currentViewSelectedItem.getStockQuantity();
        double tempViewStockPrice = currentViewSelectedItem.getStockPrice();


        admin.txtViewBarcode.setText(Integer.toString(tempViewBarcodeNum));
        admin.txtViewName.setText(tempViewStockName);
        admin.txtViewQuantity.setText(Integer.toString(tempViewStockQuantity));
        admin.txtViewPrice.setText(Double.toString(tempViewStockPrice));
    }

}
