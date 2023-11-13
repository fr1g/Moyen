package su.kami.moyen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import reactor.util.annotation.Nullable;
import su.kami.moyen.Helper.AdminCodeHelper;
import su.kami.moyen.Helper.AdminCodeHelperBridge;

import java.util.Scanner;

@SpringBootApplication
@EnableCaching
public class MoyenApplication {

    private static final String
        _newPwdReq = "Scanned new password reset request. Please re-type-in the code you prefer to override to confirm:",
        _notMatchYield = "The Password Requested not match. Try again. You still have chances: ",
        _resetComplete = "Done: reset password code. Please set the \"m.code\" property to \"inherit\" to keep this password.",
        _unknownErr = "Unknown Error happened. ";

//    @Autowired
    static AdminCodeHelper _ach = new AdminCodeHelper();

    @Value("${m.code}")
    static String updateAdminCode;

    public static void main(String[] args) {
        try{
            var configurePreload = SpringApplication.run(MoyenApplication.class, args);
            var updateAdminCodeAnother = configurePreload.getEnvironment().getProperty("m.code");
            System.out.println("get dis: " + updateAdminCode + " + " + updateAdminCodeAnother);
            if(updateAdminCode != null) if(!updateAdminCode.equals("inherit")) {
                var scan = new Scanner(System.in);
                System.out.println(_newPwdReq);
                var retry = 4;
                while(retry > 0){
                    retry--;
                    var typeIn = scan.nextLine();
                    if(typeIn != null){
                        if (updateAdminCode.equals(typeIn)) {
                           // assert _ach != null : "ASSERT: _ach == null? (C:C# --- null! \\/\\/ AdminCodeHelper? reference cannot set to a null object. )";
                            _ach.SubmitNewCode(typeIn);
                            System.out.println(_resetComplete + "\n\n============");
                        }else System.out.println(_notMatchYield + retry + "\n" + _newPwdReq);
                    } else System.out.println(_notMatchYield + retry + "\n" + _newPwdReq);
                }
            }
        }catch (Exception ex){
            System.err.println(_unknownErr);
            ex.printStackTrace();
        }
        return;
    }

}
