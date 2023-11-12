package su.kami.moyen.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import su.kami.moyen.Model.Return;

@RestController
@RequestMapping("/auth")
public class AdminAuthController {
    private Return<?> _return = new Return<>();
    private final HttpStatus ok = HttpStatus.OK;


    @PostMapping("/login")
    public ResponseEntity<String> AdminLogin(@RequestBody String body){


        return new ResponseEntity<String>(_return.New(), ok);
    }
}
