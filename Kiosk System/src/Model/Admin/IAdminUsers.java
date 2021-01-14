package Model.Admin;

public interface IAdminUsers {


    void Login();

    void AccessStock();

    void OrderStock();

    void ReplenishStockWarning(int Quantity);

    void ReplenishStockOnDelivery();


}
