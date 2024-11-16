package vn.dodientu.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.dodientu.Jwt.JwtAuthFilter;
import vn.dodientu.service.implementation.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailServiceImpl userDetailServiceImpl;
    private final JwtAuthFilter jwtAuthFilter;
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()
                )
                .authorizeHttpRequests(auth -> {
                    logger.debug("Configuring authorization rules...");
                    auth
                            .requestMatchers("/", "/WEB-INF/jsp/**", "/auth/**", "/", "/swagger-ui/**").permitAll()  // Các trang công khai
                            .requestMatchers("/user/**").hasRole("User")  // Trang dành cho USER
                            .requestMatchers("/admin/**").hasRole("Admin")  // Trang dành cho ADMIN
                            .requestMatchers("/manager/**").hasRole("Manager")   // Trang dành cho MANAGER
                            .requestMatchers("/shipper/**").hasRole("Shipper") // Trang dành cho SHIPPER
                            .anyRequest().authenticated();  // Bảo vệ tất cả các request còn lại
                })
                .userDetailsService(userDetailServiceImpl)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring() // Bỏ qua bảo mật cho các yêu cầu sau
                .requestMatchers("/swagger-ui/**", "/v3/api-docs*/**"); // Bỏ qua bảo mật cho các đường dẫn bắt đầu với /swagger-ui/** và /v3/api-docs*/**
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}




