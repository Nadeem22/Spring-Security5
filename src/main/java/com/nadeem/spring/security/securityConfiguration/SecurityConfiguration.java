package com.nadeem.spring.security.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    //overriding the configure method to implement our custom configuration
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //set your configuration on the auth object
      /* auth.inMemoryAuthentication()
                .withUser("nadeem")
                .password("nadeem")
                .roles("USER")
                .and()
                .withUser("fahad")
                .password("fahad")
                .roles("ADMIN");*/
     /* auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser(
              User.withUsername("nadeem")
                      .password("nadeem")
                      .roles("USER")
              )
              .withUser(
                      User.withUsername("fahad")
                              .password("fahad")
                              .roles("ADMIN")
              );*/
     auth.jdbcAuthentication()
             .dataSource(dataSource);
             //can configure your custom tables
            /* .usersByUsernameQuery("select username,password,enabled "
                     +"from users "
                     +"where username=?")
             .authoritiesByUsernameQuery("select username,authority"
                     +"from authorities"
                     +"where username=?");
*/
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
