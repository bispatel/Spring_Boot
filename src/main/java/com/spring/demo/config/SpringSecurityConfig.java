package com.spring.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    final String USER_ROLE="USER";
    final String ADMIN_ROLE ="ADMIN";

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
					.antMatchers("/","/css/**", "/home", "/about","/search","/api/**","/employee**").permitAll()
					.antMatchers("/admin/**","/file**","/upload**").hasAnyRole(ADMIN_ROLE)
					.antMatchers("/user/**").hasAnyRole(USER_ROLE)
					.anyRequest().authenticated()
                .and()
                    .formLogin()
					.loginPage("/login")
					.permitAll()
				.and()
                    .logout()
					.permitAll()
				.and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles(USER_ROLE)
                .and()
                .withUser("admin").password("password").roles(ADMIN_ROLE);
      
    }
}