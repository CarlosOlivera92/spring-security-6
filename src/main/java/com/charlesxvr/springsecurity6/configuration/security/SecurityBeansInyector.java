package com.charlesxvr.springsecurity6.configuration.security;

import com.charlesxvr.springsecurity6.services.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityBeansInyector {

    @Autowired
    private UserServiceImp userServiceImp;

    // Definición de un bean llamado "authenticationManager"
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Retorna un AuthenticationManager obtenido desde authenticationConfiguration
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Definición de un bean llamado "authenticationProvider"
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Creación de un objeto DaoAuthenticationProvider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // Configuración del servicio de usuario y codificador de contraseñas para el proveedor
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        // Retorna el proveedor configurado
        return provider;
    }

    // Definición de un bean llamado "passwordEncoder"
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Retorna un objeto BCryptPasswordEncoder para la codificación de contraseñas
        return new BCryptPasswordEncoder();
    }

    // Definición de un bean llamado "userDetailsService"
    @Bean
    public UserDetailsService userDetailsService() {
        // Retorna un UserDetailsService personalizado que obtiene usuarios por nombre de usuario
        return username -> {
            return userServiceImp.findByUsername(username)
                    .orElseThrow( () -> new RuntimeException("User not found"));
        };
    }
}
