package com.filmotokio.config;

import com.filmotokio.security.JwtAuthFilter;
import com.filmotokio.security.JwtUtil;
import com.filmotokio.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, MyUserDetailsService userDetailsService,JwtUtil jwtUtil) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtAuthFilter jwtFilter = new JwtAuthFilter(jwtUtil, userDetailsService);

        http
                // Desativa CSRF para facilitar o uso com API (você pode reativar depois, se quiser)
        .csrf(AbstractHttpConfigurer::disable) // <- nova forma de desabilitar CSRF

                // Sessão: apenas necessária para páginas MVC
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )

                .authorizeHttpRequests(auth -> auth
                        // Swagger/OpenAPI público
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // MVC público
                        .requestMatchers("/", "/auth/login", "/auth/register", "/css/**", "/js/**", "/images/**").permitAll()

                        // Endpoints REST públicos
                        .requestMatchers("/api/auth/**").permitAll()

                        // Protege endpoints REST com JWT
                        .requestMatchers("/api/**").authenticated()

                        // MVC privado
                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers("/film/**", "/home").hasAnyRole("USER", "ADMIN")

                        // Demais rotas
                        .anyRequest().authenticated()
                )

                // Login para MVC
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                // Adiciona o filtro JWT antes do filtro padrão
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
