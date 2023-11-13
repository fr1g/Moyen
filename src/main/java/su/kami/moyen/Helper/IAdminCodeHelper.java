package su.kami.moyen.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import su.kami.moyen.Exchange.Implement.IUserService;
import su.kami.moyen.Model.User;

import javax.annotation.PostConstruct;

public interface IAdminCodeHelper {

        public void SubmitNewCode(String newCode);

        public String GetBasedPassword();

}
