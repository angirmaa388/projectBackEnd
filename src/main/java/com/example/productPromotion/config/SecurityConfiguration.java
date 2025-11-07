package com.example.productPromotion.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
/**
 *
 * @author angirmaa
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CustomLogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        //this class supports creating security chain 

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth ->
            //this code will turn off the security to receive the frontend submission 
            auth.requestMatchers("/api/v1/auth/**", "/api/v1/users/**", "/api/v1/users/login/**")
            //permitting all the URL that allows the information
            .permitAll()
            .requestMatchers(HttpMethod.GET, "/api/v1/posts/**", "/api/v1/likes/**", "api/v1/postComment/**")
            .permitAll()
            .requestMatchers(HttpMethod.POST, "/api/v1/posts/**", "/api/v1/likes/**", "api/v1/postComment/**").authenticated()
            .anyRequest()
            .authenticated())
            .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .logout(l->l.logoutUrl("/api/v1/auth/logOut")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder
                                                    .clearContext()
                                                    ));
                                                    
            //it will accept only by token 

        return http.build();

    }
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource(){
        //this class supports accepts the cors origin 
        //And it receives all the http methods and headers 
        //Adding this class it will solve the different origin of 
        //the backend data base and frontend html 
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://127.0.0.1:6500", "http://localhost:6500"));
        configuration.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
      
    }
    


}
