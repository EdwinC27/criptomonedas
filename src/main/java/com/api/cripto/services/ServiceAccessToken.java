package com.api.cripto.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceAccessToken {
    @Value("${setIdEncriptacion}")
    private String setId;

    @Value("${keySecret}")
    public String key;


    public String getJWTToken(String username) {
        long MILISEGUNDO_HORA = 3_600_000;

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId(setId)
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + MILISEGUNDO_HORA))
                .signWith(SignatureAlgorithm.HS512,
                        key.getBytes()).compact();

        return token;
    }
}
