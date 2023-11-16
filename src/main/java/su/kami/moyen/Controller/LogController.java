package su.kami.moyen.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import su.kami.moyen.Exchange.Service.LogService;
import su.kami.moyen.Model.Return;
import su.kami.moyen.Model.Transaction;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {

    Return<String> _r = new Return();

    @Autowired
    LogService _ls;

    @PostMapping("/get")
    public HttpEntity<String> GetLogs(@RequestBody String body){
        try {
            StringBuilder returning = new StringBuilder("[");
//            List result;
            // request pattern: get:<page> or uid:<uid>:<page>
            var parsed = body.split(":");
            if(parsed[0].equals("get")){
                var result =  _ls.GetPagedLogs(Integer.parseInt(parsed[1])); // ?
//                System.out.println(result.size()); 我真的是佛了， 怎么真的只有一条记录啊
                for(var item : result){
                    returning.append(item.toString());
                }

            }else if(parsed[0].equals("uid")){
                var result =
                        _ls.GetUserPagedLogs(Integer.parseInt(parsed[1]),
                                             Integer.parseInt(parsed[2])); // ?
//                System.out.println(result.size());
                for(var item : result){
                    returning.append(item.toString());
                }

            }else return _r.NewEntity(444, "ILLEGAL");

            return _r.NewEntity(200, returning.append("]").toString()); // ?
        }catch (Exception ex){
            return _r.NewEntity(555, ex.toString());
        }
    }

    @PostMapping("/new")
    public HttpEntity<String> NewLog(@RequestBody String body){
        try {
            // pattern: <userId>:<serviceId>
            var parsed = body.split(":");
            _ls.NewLog(Integer.parseInt(parsed[0]), Integer.parseInt(parsed[1]));
            return _r.NewEntity(200, "done");
        }catch (Exception ex){
            return _r.NewEntity(555, ex.toString());
        }
    }

}
