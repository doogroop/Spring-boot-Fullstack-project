package com.cpan252.assignmentOne.cpan252assignmentOne.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import com.cpan252.assignmentOne.cpan252assignmentOne.model.User;
import com.cpan252.assignmentOne.cpan252assignmentOne.repository.UserRepository;

/**
 * Configuration class for Spring is like a holder of beans. We can use this
 * class to define beans that we want to use in our application.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException(username);
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers(toH2Console()).permitAll()
                .requestMatchers("/list")
                .hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/list")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf().ignoringRequestMatchers(toH2Console())
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .build();
    }

}