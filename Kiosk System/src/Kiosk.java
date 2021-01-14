import Controller.*;
import Controller.Admin.AdminController;
import Controller.Admin.AdminUsersController;
import Controller.Observable.StockDatabaseSystem;
import Controller.Payment.Card;
import Controller.Payment.Cash;
import Controller.Payment.CustomerController;
import Controller.Payment.Receipt;
import Model.Admin.AdminModel;
import Model.Customer.CustomerModel;
import Model.Customer.IPaymentMethod;
import Model.Admin.Observers.Orders;
import Model.DisplayStockListData;
import Model.SetUpFacade;
import Admin.Admin;
import Customer.Customer;

public class Kiosk {

    public static void main(String[] args){

        Admin av = new Admin("Admin Database Man");
        Customer ckv = new Customer("Customer Kiosk");

        AdminModel am = new AdminModel("", "");
        StockDatabaseSystem sds = new StockDatabaseSystem();
        Orders som = new Orders(sds);
        CustomerModel cm = new CustomerModel();

        IPaymentMethod cpipm = new Card(ckv, som);
        AdminController ac = new AdminController(am, av);
        StockDatabaseController sdc = new StockDatabaseController(sds, av);
        AdminUsersController auc = new AdminUsersController(av, som, am, sds, sdc);
        CustomerController cc = new CustomerController(sdc, sds, som, cm, ckv);
        DisplayStockListData dsld = new DisplayStockListData(av, sds, som);
        Card card = new Card(ckv, som);
        Cash cash = new Cash(ckv,cc, sds, som);
        Receipt r = new Receipt(cpipm, cc, ckv, sdc, card, cash);
        SetUpFacade setup = new SetUpFacade(sdc, dsld);

        setup.start(sdc);
        ac.initAdminController();
        auc.initAdminUsersController();
        card.initCPayment();
        cash.initCashPayment();
        r.initReceipt();

    }
}
