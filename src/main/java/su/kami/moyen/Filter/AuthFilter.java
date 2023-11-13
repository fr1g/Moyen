package su.kami.moyen.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {



    public AuthFilter(AuthenticationManager authm){
        this.setAuthenticationManager(authm);
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new RequestHeaderRequestMatcher("/login", "POST"));
    }

//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult)
//    throws IOException, ServletException {
//        CustomUser customUser = (CustomUser) authResult.getPrincipal();
//
//        accessLogService.logAccess(customUser.getUsername(), IpUtil.getIpAddress(request), 1, "登录成功");
//        String token = JwtUtil.createToken(customUser.getUser().getId(), customUser.getUser().getUsername());
//        Map<String, Object> map = new HashMap<>();
//        map.put("token", token);
//        ResponseUtil.out(response, Result.ok(map));
//    }

}
