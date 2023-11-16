package su.kami.moyen.Exchange.Mapper;

import org.apache.ibatis.annotations.*;
import su.kami.moyen.Model.User;

import java.util.List;

@Mapper
public interface IUsersMapper {
    @Select("select * from users where 1 = 1 limit #{page}, 6")
    List<User> GetPagedUsers(int page);

    @Select("select * from users where name like concat('%', concat(#{name,jdbcType=VARCHAR}, '%')) limit #{page,jdbcType=INTEGER}, 6")
    List<User> SearchByUserNamePaged(String name, int page); // NOT FOR MINIMAL

    @Select("select * from users where id = #{id}")
    User GetExactlyUser(int id);

    @Insert("insert into users(name, contact, balance, elsewhat, status) " +
            "value(#{name}, #{contact}, #{balance}, #{elsewhat}, 0)")
    void AddUser(User user);

    @Update("update users set status = #{status} where id = #{uid}")
    void UpdateUserStatus(int status, int uid);

    @Update("update users set balance = #{newAcc} where id = #{uid}")
    void AddUserFund(double newAcc, int uid);

    @Update("update users set contact = #{code} where id = 0")
    void UpdateAdminCode(String code);
}
