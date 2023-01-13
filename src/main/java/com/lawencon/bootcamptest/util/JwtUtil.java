package com.lawencon.bootcamptest.util;

import java.security.Key;
import java.security.KeyPair;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    
    private KeyPair keys = Keys.keyPairFor(SignatureAlgorithm.RS256);

    // membuat token
    public String createToken(Map<String,Object> claims){

        return Jwts.builder().addClaims(claims).signWith(keys.getPrivate()).compact();
    }

    // data yang didapat setelah token diproleh
    public Map<String,Object> parseToken(String token){
        return Jwts.parserBuilder().setSigningKey(keys.getPublic()).build().parseClaimsJws(token).getBody();
    }
}
