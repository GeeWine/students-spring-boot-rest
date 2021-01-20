package hu.geewine.studentsspringbootrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}password")
                .roles("USER");
    }

    private static final String[] AUTH_WHITELIST = {
            "/actuator",
            "/actuator/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/configuration/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/",
            "/swagger-ui/**",
            "/swagger-ui",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

}
