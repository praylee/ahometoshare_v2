package app.withyou.ahometoshare;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(User.withDefaultPasswordEncoder().username("user").password("password").authorities("ROLE_USER").build(),
                User.withDefaultPasswordEncoder().username("admin").password("admin").authorities("ROLE_ACTUATOR","ROLE_ADMIN","ROLE_USER").build());

    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests().mvcMatchers("/admin").permitAll().mvcMatchers("/homepage").permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/events/**").hasRole("USER")
                .antMatchers("**").permitAll().and().httpBasic();

    }

}

