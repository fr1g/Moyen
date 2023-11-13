package su.kami.moyen;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import su.kami.moyen.Helper.AdminCodeHelper;

import java.util.Scanner;

@SpringBootApplication
@EnableCaching
public class MoyenApplication {

    private static final String
        _newPwdReq = "Scanned new password reset request. Please re-type-in the code you prefer to override to confirm:",
        _notMatchYield = "The Password Requested not match. Try again. You still have chances: ",
        _resetComplete = "Done: reset password code. Please set the \"m.code\" property to \"inherit\" to keep this password.",
        _unknownErr = "Unknown Error happened. ";


    public static void main(String[] args) {
        System.out.println("Current Path: " + System.getProperty("user.dir"));
        try{
            var configurePreload = SpringApplication.run(MoyenApplication.class, args);
            var updateAdminCode = configurePreload.getEnvironment().getProperty("m.code");
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
                            var _ach = new AdminCodeHelper();
                            _ach.ApplyNew(typeIn);
                            System.out.println(_resetComplete + "\n\n============");
                            _ach.Vanish();
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