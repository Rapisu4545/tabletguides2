package com.example.tabletguides.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public WebSecurityConfig() {
    }

    protected void configure(HttpSecurity http) throws Exception {
        ((HttpSecurity)((FormLoginConfigurer)((HttpSecurity)((AuthorizedUrl)((AuthorizedUrl)http.authorizeRequests().antMatchers(new String[]{"/css/**", "/image/**", "/script/**"})).permitAll().anyRequest()).authenticated().and()).formLogin().loginPage("/login").permitAll()).and()).logout().permitAll();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder().username("tabletguides").password("1qazse4").roles(new String[]{"USER"}).build();
        return new InMemoryUserDetailsManager(new UserDetails[]{user});
    }
}
