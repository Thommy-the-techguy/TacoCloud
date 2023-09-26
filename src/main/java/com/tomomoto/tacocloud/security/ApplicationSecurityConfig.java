package com.tomomoto.tacocloud.security;

import com.tomomoto.tacocloud.dao.UserRepository;
import com.tomomoto.tacocloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ApplicationSecurityConfig {
    private UserRepository userRepository;

    @Autowired
    public ApplicationSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return (username) -> {
            User user = !userRepository.findUserByUsername(username).isEmpty() ?
                    userRepository.findUserByUsername(username).get(0) : null;
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User with username: " + username + " was not found!");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/images/*", "/login", "/register")
                        .permitAll()
                        .requestMatchers("/design/**", "/orders/**", "/logout/**")
                        .hasRole("USER")
                )
                .formLogin((login) -> login.loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/design"))
                .oauth2Login((oauth2) -> oauth2.loginPage("/login")
                        .defaultSuccessUrl("/design"))
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/"))
                .build();
    }
}