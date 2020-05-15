package com.adaptativelearning.security;

import static com.adaptativelearning.security.Constants.HEADER_AUTHORIZACION_KEY;
import static com.adaptativelearning.security.Constants.ISSUER_INFO;
import static com.adaptativelearning.security.Constants.SUPER_SECRET_KEY;
import static com.adaptativelearning.security.Constants.TOKEN_BEARER_PREFIX;

import com.adaptativelearning.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response)
    throws AuthenticationException
    {
        try
        {
            User credenciales = new ObjectMapper().readValue(request.getInputStream(),User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credenciales.getIdNumber(),
                credenciales.getPassword(),
                new ArrayList<>()));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication auth)
    throws IOException, ServletException
    {
        String token =
            Jwts.builder()
                .setIssuedAt(new Date())
                .setIssuer(ISSUER_INFO)
                .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
        response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);
    }
}
