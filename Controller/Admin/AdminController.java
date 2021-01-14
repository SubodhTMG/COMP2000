package Controller.Admin;
import Model.Admin.AdminModel;
import Admin.Admin;

public class AdminController {

    private final AdminModel adminModel;
    private final Admin admin;

    public AdminController(AdminModel am, Admin av){
        adminModel = am;
        admin = av;
        initAdminView();
    }

    public void initAdminView(){
        admin.getTxtUsername().setText(adminModel.getAdminUsername());
        admin.getTxtPassword().setText(adminModel.getAdminPassword());
    }

    public void initAdminController(){
        //Detect button press and run saveAdminUser Method
        admin.getBtnLogin().addActionListener(e -> saveAdminUser());
    }

    private void saveAdminUser(){
        //Store the user text input into the setAdminUsername
        adminModel.setAdminUsername(admin.getTxtUsername().getText());
        System.out.println("Welcome " + adminModel.getAdminUsername());
    }
}
