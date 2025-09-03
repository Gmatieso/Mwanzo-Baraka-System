package com.gmatieso.mwanzo.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmatieso.mwanzo.common.response.ApiResponse;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.gmatieso.mwanzo.common.config.ApiConfig.BASE_API_PATH;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private  static  final String[] WHITE_LIST_URLS = {
            "/api/auth/signin",
            "/auth/**",
            "/api/v1/**",
            "/api/auth/**",
            "/apidocs/**",
            "/swagger-ui/**",
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    CorsConfigurationSource corsConfigurationSource() {
        return new UrlBasedCorsConfigurationSource() {{
            registerCorsConfiguration(
                    "/**",
                    new CorsConfiguration() {{
                        setAllowedOrigins(List.of("*"));
                        setAllowedHeaders(List.of("*"));
                        setAllowedMethods(List.of(
                                HttpMethod.DELETE.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.GET.name()
                        ));
                    }}.applyPermitDefaultValues()
            );
        }};
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(customizer -> customizer.configurationSource(corsConfigurationSource()))
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(req -> req
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ASYNC).permitAll()
                        .requestMatchers(WHITE_LIST_URLS).permitAll()
//                        .requestMatchers("/api/v1/").hasAnyRole("ADMIN","SUPER_ADMIN")
                        .anyRequest()
                        .authenticated())
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutUrl(BASE_API_PATH + "/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> {
                            SecurityContextHolder.clearContext();
                            ApiResponse<String> apiResponse = new ApiResponse<>(200, "Logout successful");
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("application/json");
                            response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
                            response.getWriter().flush();
                        })
                )
                .build();
    }


}
