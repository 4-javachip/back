package com.starbucks.back.common.config;

import com.starbucks.back.common.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider oAuthAuthenticationProvider;
    private final AuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                    authorizeRequests -> authorizeRequests
                            .requestMatchers(
                                    "/api/v1/auth/**",
                                    "/api/v1/email/**",
                                    "/api/v1/agreement/**",
                                    "/api/v1/option/**",
                                    "/api/v1/product/**",
                                    "/api/v1/category/**",
                                    "/api/v1/season/**",
                                    "/api/v1/best/**",
                                    "/api/v1/event/**",
                                    "/swagger-ui/**",
                                    "/v3/api-docs/**",
                                    "/error"
//                                    "/**"
                            )
                            .permitAll()
//                               .requestMatchers("/api/v1/review/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/review/**").permitAll()  // GET 요청 허용
//                               .requestMatchers(HttpMethod.POST, "/api/v1/review/**").denyAll()   // POST 요청 차단
//                               .requestMatchers(HttpMethod.PUT, "/api/v1/review/**").denyAll()    // PUT 요청 차단
//                               .requestMatchers(HttpMethod.DELETE, "/api/v1/review/**").denyAll() // DELETE 요청 차단
                            .anyRequest()
                            .authenticated()
            )
            .sessionManagement(
                    sessionManagement -> sessionManagement
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(daoAuthenticationProvider)
            .authenticationProvider(oAuthAuthenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilter(corsFilter());
    return http.build();
}

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "https://starbucks-store.shop",
                "https://www.starbucks-store.shop",
                "https://back.starbucks-store.shop"
        ));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setExposedHeaders(List.of("Authorization, Content-Type, X-CSRF-TOKEN", "Set-Cookie"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
