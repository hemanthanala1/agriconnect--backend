package com.klef.fsad.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.klef.fsad.exam.security.JwtFilter;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain security(HttpSecurity http, JwtFilter filter) throws Exception {

        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // ✅ ROOT + HEALTH (FIXES 403)
                .requestMatchers("/", "/health").permitAll()

                // ✅ AUTH (PUBLIC)
                .requestMatchers("/api/v1/auth/**").permitAll()

                // ✅ SWAGGER (PUBLIC)
                .requestMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/webjars/**"
                ).permitAll()

                // ✅ FARMER
                .requestMatchers("/api/v1/crops/**").hasAnyRole("FARMER", "ADMIN")
                .requestMatchers("/api/v1/farmer/**").hasRole("FARMER")
                .requestMatchers("/api/v1/advice/ask").hasRole("FARMER")

                // ✅ EXPERT
                .requestMatchers("/api/v1/expert/**").hasRole("EXPERT")
                .requestMatchers("/api/v1/advice/answer/**").hasRole("EXPERT")

                // ✅ ADMIN
                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")

                // 🔒 EVERYTHING ELSE
                .anyRequest().authenticated()
            );

        // ✅ JWT FILTER
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ CORS CONFIG
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(
        	    "http://localhost:3000",
        	    "http://localhost:5173",
        	    "http://127.0.0.1:5173",
        	    "http://127.0.0.1:3000",

        	    // ✅ ADD THIS
        	    "https://agriconnect-i26x.onrender.com"
        	));

        configuration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));

        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}