package com.nadeem.spring.security.securityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //overriding the configure method to implement our custom configuration
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //set your configuration on the auth object
        auth.inMemoryAuthentication()
                .withUser("nadeem")
                .password("nadeem")
                .roles("USER")
                .and()
                .withUser("fahad")
                .password("fahad")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
              // .antMatchers("/","static/css","static/js").permitAll()
               .antMatchers("/admin").hasRole("ADMIN")
               .antMatchers("/user").hasAnyRole("USER","ADMIN")
               .antMatchers("/").permitAll()
               .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
