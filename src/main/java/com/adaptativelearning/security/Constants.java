package com.adaptativelearning.security;

public class Constants
{
    public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    public static final String ISSUER_INFO = "https://www.autentia.com/";
    public static final String SUPER_SECRET_KEY = "1234";
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
    public static final String[] PUBLIC_MATCHERS =
        {"/login", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**"};
}
