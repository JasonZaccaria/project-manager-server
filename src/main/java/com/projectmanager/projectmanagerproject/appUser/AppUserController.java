package com.projectmanager.projectmanagerproject.appUser;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.projectmanager.projectmanagerproject.dto.AuthenticationRequest;
import com.projectmanager.projectmanagerproject.dto.AuthenticationResponse;
import com.projectmanager.projectmanagerproject.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppUserController {
    
    private final AppUserServiceImpl appUserServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @GetMapping("/test")
    public String testRoute() {
        return "hi";
    }

    @PostMapping("/register")
    public AppUser registerUser(@RequestBody AppUser appUser) {
        return appUserServiceImpl.saveUser(appUser);
    } 

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                authenticationRequest.getPassword()));
    } catch (BadCredentialsException exception) {
        throw new Exception("incorrect username or password");
    }
        
        final UserDetails userDetails = appUserServiceImpl.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.createToken(userDetails.getUsername(), userDetails.getPassword());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
