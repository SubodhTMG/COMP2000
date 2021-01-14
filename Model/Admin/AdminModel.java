package Model.Admin;

public class AdminModel {

    //region Variables
    String adminUsername;
    String adminPassword;
    //endregion

    public AdminModel(String adminUsernames, String adminPasswords){
        this.adminUsername = adminUsernames;
        this.adminPassword = adminPasswords;
    }

    //region Get/Set Methods to Store and Retrieve file read Admin Details
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
    //endregion
}
