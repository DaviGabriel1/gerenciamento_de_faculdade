package com.gerenciamentofaculdade.gerenciamento_de_faculdade.config.security;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final JwtFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(
                                        "/auth/**",
                                        "/v1/api-docs/",
                                        "/v2/api-docs/",
                                        "/v3/api-docs/**",
                                        "/swagger-ui/",
                                        "/swagger-ui.html",
                                         "/swagger-resources/",
                                        "/swagger-resources/**",
                                        "/webjars/**",
                                        "/swagger-ui.html"
                                ).permitAll()
                                .requestMatchers(
                                        "/coordenador/**"
                                ).hasAuthority("coordenador")
                                .requestMatchers(
                                        "/all/**"
                                ).hasAnyAuthority("USER","ADMIN") //usar hasRole apenas se tiver ROLE_ no banco de dados
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
