package com.grocery.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("vishal").password("vishal").roles("ADMIN");
        auth
        .inMemoryAuthentication()  
            .withUser("preeti").password("preeti").roles("NORMAL");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/grocery/items/**").hasRole("ADMIN")
                .antMatchers("/api/order/items/**").hasRole("NORMAL")
                .anyRequest().authenticated()
                .and()
            .httpBasic();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder()
    {
    	return NoOpPasswordEncoder.getInstance();
    }
}
