package com.kubel.security;

public class SecurityConstants {
    public static final String SECRET = "qwebnbndbasdanmaxbezmen";
    public static final long EXPIRATION_TIME = 1000_000;// 15 min
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SING_UP_URL = "/users/registration";
    public static final String CONFIRM_URL = "/confirm"+"*";
}
