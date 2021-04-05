package com.kubel.security;

public class SecurityConstants {
    private SecurityConstants(){

    }
    public static final String SECRET = "qwebnbndbasdanmaxbezmen";
    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SING_UP_URL = "/users/registration";
    public static final String CONFIRM_URL = "/confirm"+"*";
}
