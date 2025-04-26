package com.empresa.sistema.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca {
    

    @Bean
    public UserDetailsService  userDetailsService(){
        UserDetails user1 = User.builder().username("fernando").password("{noop}fernando123").build();
        return new InMemoryUserDetailsManager(user1);
    }

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(req -> {
            req.requestMatchers("/css/**", "/assets/**").permitAll();
            req.anyRequest().authenticated();
        }).formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/login?logout").permitAll())
        .logout(logout -> logout.logoutSuccessUrl("/"))
        .build();
    }
}
