package com.one.forohub.controller;

import com.one.forohub.dto.UserAuthenticationData;
import com.one.forohub.model.User;
import com.one.forohub.dto.JwtTokenData;
import com.one.forohub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<JwtTokenData> authenticateUser(@RequestBody @Valid UserAuthenticationData userAuthenticationData) {
        try {
            Authentication authenticationToken = createAuthenticationToken(userAuthenticationData);
            Authentication authenticatedUser = authenticate(authenticationToken);
            String JWTtoken = generateJwtToken(authenticatedUser);

            JwtTokenData jwtTokenData = new JwtTokenData(JWTtoken);
            return ResponseEntity.ok(jwtTokenData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private Authentication createAuthenticationToken(UserAuthenticationData userAuthenticationData) {
        return new UsernamePasswordAuthenticationToken(
                userAuthenticationData.username(),
                userAuthenticationData.password()
        );
    }

    private Authentication authenticate(Authentication authenticationToken) {
        return authenticationManager.authenticate(authenticationToken);
    }

    private String generateJwtToken(Authentication authenticatedUser) {
        return tokenService.generateToken((User) authenticatedUser.getPrincipal());
    }
}
