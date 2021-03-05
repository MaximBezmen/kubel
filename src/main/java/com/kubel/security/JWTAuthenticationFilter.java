package com.kubel.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubel.exception.CustomAuthenticationException;
import com.kubel.service.dto.AccountDto;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.kubel.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private AuthenticationFailureHandler failureHandler;
    private RememberMeServices rememberMeServices;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/users/login");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            AccountDto accountDto = new ObjectMapper().readValue(request.getInputStream(), AccountDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    accountDto.getLogin(),
                    accountDto.getPassword(),
                    new ArrayList<>()));
        } catch (IOException e) {
            throw new CustomAuthenticationException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        String token = JWT.create().withSubject(((UserPrincipal) authResult.getPrincipal()).getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        String body = ((UserPrincipal) authResult.getPrincipal()).getId() + " " + TOKEN_PREFIX + token;
        response.getWriter().write(body);
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        this.failureHandler = new AuthenticationFailureHandler();
        this.rememberMeServices = new NullRememberMeServices();
        this.logger.trace("Failed to process authentication request", failed);
        this.logger.trace("Cleared SecurityContextHolder");
        this.logger.trace("Handling authentication failure");
        this.rememberMeServices.loginFail(request, response);
        this.failureHandler.onAuthenticationFailure(request, response, failed);

    }
}
