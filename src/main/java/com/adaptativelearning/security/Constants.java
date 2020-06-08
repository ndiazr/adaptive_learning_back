package com.adaptativelearning.security;

public class Constants
{
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    public static final String ISSUER_INFO = "https://www.autentia.com/";
    public static final String SUPER_SECRET_KEY = "1234";
    public static final String[] PUBLIC_MATCHERS =
        {"/auth/**", "/schools/findAll", "/roles/findAll", "/swagger-ui.html",
            "/swagger-resources/**", "/v2/api-docs", "/webjars/**"};
}
