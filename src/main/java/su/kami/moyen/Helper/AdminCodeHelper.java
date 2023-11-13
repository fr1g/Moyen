package su.kami.moyen.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.kami.moyen.Exchange.Implement.IUserService;
import su.kami.moyen.Exchange.Service.UserService;
import su.kami.moyen.Model.User;

import javax.annotation.PostConstruct;

//@Component
@Service
@Transactional
public class AdminCodeHelper implements IAdminCodeHelper {
//public class AdminCodeHelper {

    @Autowired
    UserService _u;

    public final String NAME = "root";
    @Value("${m.code}")
    String updateAdminCode;

    public String PWD_HASHED = "";
    protected su.kami.moyen.Model.User ROOT = new User("initialRootUser");

    public void SubmitNewCode(String newCode){
        var _temp = MD5CryptHelper.encrypt(newCode);
        _u.UpdateAdmin(_temp);
    }

    public String GetBasedPassword(){
        try{
            if(!_u.IsAdminExist()){
                var _seed = new User();
                _seed.setId(0);
                _seed.setContact(MD5CryptHelper.encrypt(updateAdminCode));
                _seed.setName("root");
                _u.CreateUser(_seed);
            }
            Thread.sleep(5000);
            this.PWD_HASHED = _u.GetExactlyUser(0).getContact();
            return this.PWD_HASHED;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "[err]";
    }


    public AdminCodeHelper(){
        this._u = new UserService();
    }
//    @PostConstruct
//    public void init(){
//        this.PWD_HASHED = _u.GetExactlyUser(0).getContact();
//    }

}
