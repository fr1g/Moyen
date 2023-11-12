package su.kami.moyen.Exchange.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import su.kami.moyen.Model.Service;

import java.util.List;

public interface IServicesMapper {
    @Select("select * from services where 1 = 1 limit #{page}, 6")
    List<Service> GetPagedServices(int page);

    @Select("select * from services where 1 = 1")
    List<Service> GetServices(); // initial get into redis.

    @Select("select * from services where id = #{id}")
    Service GetExactlyService(int id);

    @Update("update services set " +
            "id = #{id}, " +
            "name = #{name}, " +
            "`describe` = #{describe}, " +
            "cost = #{cost} " +
            "plus = #{plus} " +
            "where id = #{id}")
    void UpdateService(Service service); //?

    @Update("update services set plus = #{plus} where id = #{id}")
    void UpdatePlus(String plus, int id);

    @Delete("delete * from services where id = #{id}")
    void RemoveSingleService(int id);

    @Insert("insert into services(name, `describe`, cost, plus) " +
            "value(#{name}, #{describe}, #{cost}, #{plus})")
    void NewService(Service service);


}
