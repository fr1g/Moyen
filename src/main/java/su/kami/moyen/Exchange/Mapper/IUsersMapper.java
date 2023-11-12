package su.kami.moyen.Exchange.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import su.kami.moyen.Model.User;

import java.util.List;

public interface IUsersMapper {
    @Select("select * from users where 1 = 1 limit #{page}, 6")
    List<User> GetPagedUsers(int page);

    @Select("select * from users where id = #{id}")
    User GetExactlyUser(int id);

    @Delete("delete * from users where id = #{id}")
    void RemoveUser(int id);

    @Insert("insert into users(name, contact, balance, elsewhat, status) " +
            "value(#{name}, #{contact}, #{balance}, #{elsewhat}, 0)")
    void AddUser(User user);

    @Update("update users set status = #{status} where id = #{uid}")
    void UpdateUserStatus(int status, int uid);
}
