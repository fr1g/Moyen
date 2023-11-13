package su.kami.moyen.Helper;

import org.springframework.beans.factory.annotation.Autowired;

public class AdminCodeHelperBridge {
    @Autowired
    AdminCodeHelper _ach;

    public AdminCodeHelper Create(){
        return _ach;
    }
}
