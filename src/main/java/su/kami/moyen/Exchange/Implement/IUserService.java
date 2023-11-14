package su.kami.moyen.Exchange.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.kami.moyen.Exchange.Mapper.IUsersMapper;
import su.kami.moyen.Model.User;

import java.util.List;

public interface IUserService {

    boolean IsAdminExist();
    void UpdateAdmin(String code);
    void CreateUser(User user);
    void SwitchUserStatus(int uid);
    void UserAddFund(int uid, double add);
    List<User> GetUsersPaged(int page);
    User GetExactlyUser(int uid);

}
