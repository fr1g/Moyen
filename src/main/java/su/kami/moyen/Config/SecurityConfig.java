package su.kami.moyen.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import su.kami.moyen.Helper.AdminCodeHelper;

@Configuration
@EnableGlobalMethodSecurity//(prePostEnabled = true)
public class SecurityConfig {

    private AdminCodeHelper _ach = new AdminCodeHelper();
    @Bean
    SecurityFilterChain filterChain(HttpSecurity hs) throws Exception{
        return hs.authorizeRequests()
                 .antMatchers("/**").hasRole("admin")
                 .and().formLogin().and().rememberMe().and()
                 .build();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User
                .withUsername("root")
                .password("password")
                .roles("admin")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
