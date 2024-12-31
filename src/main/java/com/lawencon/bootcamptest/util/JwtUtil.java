package com.lawencon.bootcamptest.util;

import java.security.KeyPair;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    
    private KeyPair keys = Keys.keyPairFor(SignatureAlgorithm.RS256);

    /*
     * create token
     */
    public String createToken(Map<String,Object> claims){
        return Jwts.builder().addClaims(claims).signWith(keys.getPrivate()).compact();
    }

    /*
     * get data token
     */
    public Map<String,Object> parseToken(String token){
        return Jwts.parserBuilder().setSigningKey(keys.getPublic()).build().parseClaimsJws(token).getBody();
    }
}
