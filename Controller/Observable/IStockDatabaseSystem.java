package Controller.Observable;

import Model.Admin.Observers.IOrders;

public interface IStockDatabaseSystem {

    //region Abstract Methods used in StockDatabaseSystem
    void Add(IOrders stockOrdersModel);

    void Remove(IOrders stockOrdersModel);
    //endregion
}
