package su.kami.moyen.Exchange.Mapper;

import org.apache.ibatis.annotations.*;
import su.kami.moyen.Model.Transaction;

import java.util.List;

public interface ITransactionMapper {
    @Select("select * from logs where 1 = 1 limit #{page}, 6")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "user", one = @One(select = "su.kami.moyen.Exchange.Mapper.IUsersMapper.GetExactlyUser")),
            @Result(column = "sid", property = "service", one = @One(select = "su.kami.moyen.Exchange.Mapper.IServicesMapper.GetExactlyService")),
            @Result(column = "date", property = "date"),
    })
    List<Transaction> GetPagedLogs(int page);

    @Select("select * from logs inner join users on logs.uid = users.id where 1 = 1 limit #{page}, 6")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "user", one = @One(select = "su.kami.moyen.Exchange.Mapper.IUsersMapper.GetExactlyUser")),
            @Result(column = "sid", property = "service", one = @One(select = "su.kami.moyen.Exchange.Mapper.IServicesMapper.GetExactlyService")),
            @Result(column = "date", property = "date"),
    })
    List<Transaction> GetUserPagedLogs(int page);

    /*
    *
    * */
    @Insert("insert into logs(uid, sid) value(#{uid}, #{sid})")
    void NewLog(int uid, int sid);
}
