package su.kami.moyen.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import su.kami.moyen.Exchange.Service.ServiceService;
import su.kami.moyen.Model.Return;
import su.kami.moyen.Model.Service;

import javax.xml.transform.Result;

@Controller
@RequestMapping("/service")
public class ServiceController {

    Return<String> _r = new Return<>();

    @Autowired
    ServiceService _ss;

    @PostMapping("/get")
    public HttpEntity<String> GetServices(@RequestBody String body){
        try { // body only page
            int page = Integer.parseInt(body);
            var result = _ss.GetPagedServices(page);
            StringBuilder sb = new StringBuilder("[");
            for(var item : result){
                sb.append(item.toString());
            }
            return _r.NewEntity(200, sb.append("]").toString());

        }catch (Exception ex){
            return _r.NewEntity(555, ex.toString());
        }
    }

    @PostMapping("/create")
    public HttpEntity<String> NewServices(@RequestBody String body){
        try { // body pattern: only provide <name>:::<describe>:::<cost(double)>
            var parsed = body.split(":::");
            var ser = new Service();
            ser.setName(parsed[0]);
            ser.setDescribe(parsed[1]);
            ser.setCost(Double.parseDouble(parsed[2]));
            ser.setPlus("");
            _ss.NewService(ser);
            return _r.NewEntity(200, "done");
        }catch (Exception ex){
            return _r.NewEntity(555, "Maybe Illegal, view: " + ex.toString());
        }
    }

    @PostMapping("/update")
    public HttpEntity<String> UpdateServiceTag(@RequestBody String body){ // which named as: Plus, hash-start, only exist one
        try {// pattern: <sid>:<new-tag>
            var parsed = body.split(":");
            _ss.UpdatePlus(parsed[1], Integer.parseInt(parsed[0]));
            return _r.NewEntity(200, "done");
        }catch (Exception ex){
            return _r.NewEntity(555, ex.toString());
        }
    }



}
