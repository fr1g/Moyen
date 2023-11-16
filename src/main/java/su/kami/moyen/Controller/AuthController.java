package su.kami.moyen.Controller;

import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import su.kami.moyen.Model.Return;

@Controller
@RequestMapping("/sync")
public class AuthController {

    Return<String> _r = new Return<>();

    @PostMapping("/signout")
    public HttpEntity<String> Vanish(@RequestBody String body){

        try {
            // body pattern: only a string of token?
//            Jwts. 行吧！并不能销毁token
        }catch (Exception ex){

        }

        return _r.NewEntity(202, "unknown");
    }
}
