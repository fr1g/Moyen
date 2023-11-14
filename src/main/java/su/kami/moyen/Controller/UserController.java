package su.kami.moyen.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import su.kami.moyen.Exchange.Service.UserService;
import su.kami.moyen.Model.Return;
import su.kami.moyen.Model.User;

@Controller
@RequestMapping("/customer")
public class UserController {
    Return<String> _r = new Return();

    @Autowired
    UserService _u;

    @PostMapping("/create")
    public HttpEntity<String> CreateUser(@RequestBody String body) {
        try {
            // body phrase: <name>&&&<contact>
            var user = new User();
            var parsed = body.split("&&&");
            user.setName(parsed[0]);
            user.setContact(parsed[1]);
            user.setBalance(0);
            user.setElsewhat("");
            _u.CreateUser(user);
            return _r.NewEntity(200, "done");
        } catch (Exception ex) {
            return _r.NewEntity(555, ex.toString());
        }
    }

    @GetMapping("/addfund")
    public HttpEntity<String> AddFund(@RequestBody String body) {
        try {
            // body phrase: <uid>&&&<x.xx double>
            var parsed = body.split("&&&");
            var targetUserId = Integer.parseInt(parsed[0]);
            var charge = Double.parseDouble(parsed[1]);

            return _r.NewEntity(200, "done");
        } catch (Exception ex) {
            return _r.NewEntity(555, ex.toString());
        }
    }

    @PostMapping("get")
    public HttpEntity<String> GetPaged(@RequestBody String body) {
        try {
            // body pattern: p:<page> or u:<uid>
            var parsed = body.split(":");
            if (parsed[0].equals("u")) {
                var result = _u.GetExactlyUser(Integer.parseInt(parsed[1]));
                return _r.NewEntity(200, result.toString());
            } else if (parsed[0].equals("p")) {
                var result = _u.GetUsersPaged(Integer.parseInt(parsed[1]));
                var returning = "[";
                for (var item : result) {
                    if(item.getId() != 0) returning += item.toString();
                }
                return _r.NewEntity(200, returning + "]");
            } else return _r.NewEntity(444, "ILLEGAL");
        } catch (Exception ex) {
            return _r.NewEntity(555, ex.toString());
        }
    }

    @PostMapping("switch")
    public HttpEntity<String> StatSwitch(@RequestBody String body) {
        try {
            // body pattern: only: uid
            if(Integer.parseInt(body) == 0) return _r.NewEntity(444, "ILLEGAL");
            _u.SwitchUserStatus(Integer.parseInt(body));
            return _r.NewEntity(200, "done");
            }
        catch(Exception ex)
        {
            return _r.NewEntity(555, ex.toString());
        }
    }

    // todo: after minimal done, complete: user add fund per each user action;;; and the status switcher.
}
