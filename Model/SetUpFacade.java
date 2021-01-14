package Model;

import Controller.StockDatabaseController;

public class SetUpFacade {

    private StockDatabaseController stockDatabaseController;
    private DisplayStockListData displayStockListData;

    //Set up class objects
    public SetUpFacade(StockDatabaseController sdc, DisplayStockListData dsld){
        this.stockDatabaseController = sdc;
        this.displayStockListData = dsld;
    }

    //Run Method when program begins
    public void start(StockDatabaseController sdc){
        stockDatabaseController.LoadKioskData();
        displayStockListData.SetUpLstData();
    }
}
