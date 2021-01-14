package Model.Admin;

public interface IAdminUsers {

    //region Interface for AdminUsers
    void Login();

    void AccessStock();

    void OrderStock();

    void ReplenishStockWarning(int Quantity);

    void ReplenishStockOnDelivery();
    //endregion

}
