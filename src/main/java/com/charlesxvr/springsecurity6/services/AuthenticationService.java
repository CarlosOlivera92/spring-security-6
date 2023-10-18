package com.charlesxvr.springsecurity6.services;

import com.charlesxvr.springsecurity6.dto.AuthenticationRequest;
import com.charlesxvr.springsecurity6.dto.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
