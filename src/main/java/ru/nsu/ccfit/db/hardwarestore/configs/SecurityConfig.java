package ru.nsu.ccfit.db.hardwarestore.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.nsu.ccfit.db.hardwarestore.services.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1",
                        "/api/v1/products/{productType}",
                        "/api/v1/login",
                        "/api/v1/register/**"
                ).permitAll()
                .requestMatchers(
                        "/api/v1/personal-area/**",
                        "/api/v1/products/add-to-cart/**",
                        "/api/v1/cart/**",
                        "/api/v1/orders/**",
                        "/api/v1/cash/**"
                ).authenticated()
                .requestMatchers("/api/v1/products/add-product")
                .hasRole("ADMIN")
                .and()
                .formLogin(form -> form
                        .loginPage("/api/v1/login")
                        .defaultSuccessUrl("/api/v1/personal-area")
                        .loginProcessingUrl("/api/v1/login")
                        .failureUrl("/api/v1/login?error=true")
                        .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/personal-area/exit")).permitAll()
                );

        return http.build();
    }
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/css/**", "/images/**");
    }
}
