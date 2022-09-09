package com.projectmanager.projectmanagerproject.util;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUtil {

    public String createToken(String email, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("auth0")
                    //.withClaim("id", id)
                    .withClaim("email", email)
                    .withClaim("password", password)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            return null;
        }
    }
    
    public DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
            //return jwt.getHeader(); //we can change this out for what data we need back. OR just return decodedjwt!!!

        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String getTokenString(DecodedJWT jwt) {
        return jwt.getToken();
    }

    public String getUsernameFromToken(DecodedJWT jwt) {
        return jwt.getClaim("email").asString();
    }

    //i need another method to trya nd get the uername with just a string of jwt isntead of passing in decoded
    //i may have to just validate the user first and then move foward. Although idk how much
    //a difference in logic that will be. All we really need to do is verify them and then add
    //that specific user to our usercontext so our application knows who that is for further requests
    
    public Map<String, Claim> getClaims(DecodedJWT jwt) {
        return jwt.getClaims();
    }

    public Date getExpiration(DecodedJWT jwt) {
        return jwt.getExpiresAt();
    }
}
