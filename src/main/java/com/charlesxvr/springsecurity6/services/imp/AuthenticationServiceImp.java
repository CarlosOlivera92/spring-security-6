package com.charlesxvr.springsecurity6.services.imp;

import com.charlesxvr.springsecurity6.dto.AuthenticationRequest;
import com.charlesxvr.springsecurity6.dto.AuthenticationResponse;
import com.charlesxvr.springsecurity6.models.User;
import com.charlesxvr.springsecurity6.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImp implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private JwtServiceImp jwtServiceImp;
    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        authenticationManager.authenticate(authToken);
        User user = userServiceImp.getByUsername(authenticationRequest.getUsername())
                .orElseThrow( () -> new RuntimeException("User not found"));
        String jwt = jwtServiceImp.generateToken(user, generateExtraClaims(user));
        return new AuthenticationResponse(jwt);
    }
    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole());
        return extraClaims;
    }
}
