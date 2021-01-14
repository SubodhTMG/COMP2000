package Model;

import Controller.StockDatabaseController;

public class SetUpFacade {

    private StockDatabaseController stockDatabaseController;
    private DisplayStockListData displayStockListData;


    public SetUpFacade(StockDatabaseController sdc, DisplayStockListData dsld){
        this.stockDatabaseController = sdc;
        this.displayStockListData = dsld;
    }


    public void start(StockDatabaseController sdc){
        stockDatabaseController.LoadKioskData();
        displayStockListData.SetUpLstData();
    }
}
