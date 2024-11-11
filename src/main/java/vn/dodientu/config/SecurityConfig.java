package vn.dodientu.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/home/**", "/auth/**", "/", "/swagger-ui/**").permitAll()  // Các trang công khai
                .requestMatchers("/user/**").hasRole("USER")  // Trang dành cho USER
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Trang dành cho ADMIN
                .requestMatchers("/manager/**").hasRole("MANAGER")  // Trang dành cho MANAGER
                .requestMatchers("/shipper/**").hasRole("SHIPPER")  // Trang dành cho SHIPPER
                .anyRequest().authenticated()  // Bảo vệ tất cả các request còn lại
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/home/login")   // Đường dẫn trang login
                .loginProcessingUrl("/auth/login") // Đường xử lý đăng nhập
                .defaultSuccessUrl("/home/index", true) // Sau khi đăng nhập thành công chuyển hướng đến trang home
                .failureUrl("/home/login?error=true")  // Nếu đăng nhập thất bại thì chuyển đến trang login với thông báo lỗi
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home/login?logout=true")  // Sau khi logout thành công, quay về login page
            );

        return http.build();  // Sử dụng http.build() trong Spring Security 6.x
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring() // Bỏ qua bảo mật cho các yêu cầu sau
                .requestMatchers("/swagger-ui/**", "/v3/api-docs*/**"); // Bỏ qua bảo mật cho các đường dẫn bắt đầu với /swagger-ui/** và /v3/api-docs*/**
    }
    
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/assets/**");
    }


}
