package su.kami.moyen.Exchange.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.kami.moyen.Exchange.Implement.IUserService;
import su.kami.moyen.Exchange.Mapper.*;
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
    public void CreateUser(User user) {

    }

    @Override
    public void DisableUser(int uid) {

    }

    @Override
    public List<User> GetUsersPaged(int page) {
        return null;
    }

    @Override
    public User GetExactlyUser(int uid) {
        return null;
    }
}
