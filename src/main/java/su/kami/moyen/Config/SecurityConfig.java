package su.kami.moyen.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import su.kami.moyen.Filter.AuthFilter;
import su.kami.moyen.Helper.AdminCodeHelper;
import su.kami.moyen.Helper.EncryptBridgeHelper;
import su.kami.moyen.Model.Return;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService uds;

    @Autowired
    private EncryptBridgeHelper _ebh;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().cors()
            .and().authorizeRequests().antMatchers("/login").permitAll()
            .anyRequest().authenticated()
            .and().formLogin()
                .failureHandler(FailHandler())
                .successHandler(SuccHandler())
            .and().addFilter(new AuthFilter(authenticationManager()));
//                .and().build();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        AdminCodeHelper _ach = new AdminCodeHelper();
        var code = _ach.Code;
        if(_ach.Code.equals("init")) code = _ach.ForceCode();
        System.out.println("Memory set pwd match: to: " + code);
        UserDetails user = User
                .withUsername("root")
                .password("{noop}" + code)
                .roles("admin")
                .build();
        _ach.Vanish();
        return new InMemoryUserDetailsManager(user);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(uds)
                .passwordEncoder(_ebh);
    }

    protected AuthenticationFailureHandler FailHandler(){
        var _r = new Return<String>(440, "not_logged_in");
        return (request, response, exception) -> {
            response.getWriter().write(_r.toString());
            response.setStatus(200);
        };
    }

    protected AuthenticationSuccessHandler SuccHandler(){
        var _r = new Return<String>(200, "success-token: ");
        return (request, response, exception) -> {
            response.getWriter().write(_r.toString());
            response.setStatus(200);
        };
    }

}
