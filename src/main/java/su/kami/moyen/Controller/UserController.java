package su.kami.moyen.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import su.kami.moyen.Model.Return;

@Controller
@RequestMapping("/customer")
public class UserController {
    Return<?> _r = new Return();

    @PostMapping("/create")
    public HttpEntity<String> CreateUser(@RequestBody String body){

        return _r.NewEntity();
    }
}
