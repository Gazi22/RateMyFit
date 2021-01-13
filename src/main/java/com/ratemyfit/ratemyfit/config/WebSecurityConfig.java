package com.ratemyfit.ratemyfit.config;

import javax.sql.DataSource;

import com.ratemyfit.ratemyfit.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/new").authenticated()
                .antMatchers("/my_fit").authenticated()
                .antMatchers("/ratings_comments").authenticated()
                .antMatchers("/find_comments").authenticated()
                .antMatchers("/save").authenticated()
                .antMatchers("/save_c/{id}").authenticated()
                .antMatchers("/save_r/{id}").authenticated()
                .antMatchers("/edit/**").authenticated()
                .antMatchers("/delete/**").authenticated()
                .antMatchers("/profile").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                /*.loginPage("/login")*/
                .permitAll()
                .usernameParameter("email")
                .defaultSuccessUrl("/homepage")
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
    }


}