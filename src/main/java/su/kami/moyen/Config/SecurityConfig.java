package su.kami.moyen.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import su.kami.moyen.Helper.AdminCodeHelper;
import su.kami.moyen.Model.Return;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private AdminCodeHelper _ach = new AdminCodeHelper();
    @Bean
    SecurityFilterChain filterChain(HttpSecurity hs) throws Exception{
        return hs.authorizeRequests()
                 .antMatchers("/**").hasRole("admin")
                 .and().csrf()
                 .and().formLogin().failureHandler(Handler())
                 .and().rememberMe()
                 .and()
                 .build();
    }

    @Value("${m.code}")
    String Code;
    @Bean
    public UserDetailsService userDetailsService(){
        System.out.println("Read Code: " + Code);
        UserDetails user = User
                .withUsername("root")
                .password("{noop}" + Code)
                .roles("admin")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    protected AuthenticationFailureHandler Handler(){
        var _r = new Return<String>(440, "not_logged_in");
        return (request, response, exception) -> {
            response.getWriter().write(_r.toString());
            response.setStatus(200);
        };
    }

}
