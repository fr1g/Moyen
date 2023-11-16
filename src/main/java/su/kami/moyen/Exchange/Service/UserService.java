package su.kami.moyen.Exchange.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.kami.moyen.Exchange.Implement.IUserService;
import su.kami.moyen.Exchange.Mapper.*;
import su.kami.moyen.Helper.PageHelper;
import su.kami.moyen.Model.User;

import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    su.kami.moyen.Exchange.Mapper.IUsersMapper _user;

    @Override
    public boolean IsAdminExist() {
        try{
            var query = _user.GetExactlyUser(0);
            if(query.getContact().equals("")) return false;
            else return true;
        }catch (Exception ex){
            System.err.println("AdminExist?: err: internal. ?probably: not exist, or failed connection to DB");
        }
        finally {
            return false;
        }
    }

    @Override
    public void UpdateAdmin(String code) {
        _user.UpdateAdminCode(code);
    }

    @Override
    public void CreateUser(User user) {
        _user.AddUser(user);
    }

    @Override
    public void SwitchUserStatus(int uid) {
        var _preset = _user.GetExactlyUser(uid).getStatus();
        _user.UpdateUserStatus((_preset == 0 ? 9 : 0), uid);
    }

    @Override
    public void UserAddFund(int uid, double add) {
        var _preset = _user.GetExactlyUser(uid).getBalance();
        System.out.println(_preset + add);
        _user.AddUserFund(_preset + add, uid);
    }

    @Override
    public List<User> GetUsersPaged(int page) {
        return _user.GetPagedUsers(PageHelper.P(page));
    }

    @Override
    public List<User> SearchUsersPaged(String name, int page) {
        return _user.SearchByUserNamePaged(name, PageHelper.P(page));
    }

    @Override
    public User GetExactlyUser(int uid) {
        return _user.GetExactlyUser(uid);
    }
}
