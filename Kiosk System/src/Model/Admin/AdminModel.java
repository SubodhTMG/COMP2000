package Model.Admin;

public class AdminModel {


    String adminUsername;
    String adminPassword;


    public AdminModel(String adminUsernames, String adminPasswords){
        this.adminUsername = adminUsernames;
        this.adminPassword = adminPasswords;
    }


    public String getAdminUsername(){
        return adminUsername;
    }

    public void setAdminUsername(String AdminUsername){
        this.adminUsername = AdminUsername;
    }

    public String getAdminPassword(){
        return adminPassword;
    }

    public void setAdminPassword(String AdminPassword){
        this.adminPassword = AdminPassword;
    }

}
