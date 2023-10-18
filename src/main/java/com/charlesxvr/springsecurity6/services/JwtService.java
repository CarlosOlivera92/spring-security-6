package com.charlesxvr.springsecurity6.services;

import com.charlesxvr.springsecurity6.models.User;

import java.util.Map;

public interface JwtService {
    String generateToken(User user, Map<String, Object> generateExtraClaims);
}
