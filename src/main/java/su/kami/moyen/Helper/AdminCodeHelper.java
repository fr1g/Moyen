package su.kami.moyen.Helper;

import su.kami.moyen.Model.User;

public class AdminCodeHelper {

    public final String NAME = "root";
    public String PWD_HASH = "";
    protected su.kami.moyen.Model.User ROOT = new User("initialRootUser");

    public void SubmitNewCode(String newCode){
        var _temp = MD5CryptHelper.encrypt(newCode);

    }

    public void InitSecurity(){

    }




    public AdminCodeHelper(){

    }
}
