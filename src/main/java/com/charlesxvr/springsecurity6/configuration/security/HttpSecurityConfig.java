package com.charlesxvr.springsecurity6.configuration.security;

import com.charlesxvr.springsecurity6.utils.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationProvider authenticationProvider) throws Exception {
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests( authConfig -> {

                    //Public endpoints
                    authConfig.requestMatchers(HttpMethod.GET, "/api/auth").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll();

                    authConfig.requestMatchers(HttpMethod.POST, "/api/auth").permitAll();

                    authConfig.requestMatchers("/error").permitAll();
                    //Private endpoints
                    authConfig.requestMatchers(HttpMethod.GET, "/api/products").hasAuthority(Permission.READ_ALL_PRODUCTS.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/api/products").hasAuthority(Permission.SAVE_ONE_PRODUCT.name());

                    authConfig.anyRequest().denyAll();
                } );


        return httpSecurity.build();
    }
}
