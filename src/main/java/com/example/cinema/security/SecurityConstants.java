package com.example.cinema.security;

public final class SecurityConstants {

//    public static final String AUTH_LOGIN_URL = "/authenticate";

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    public static final String JWT_SECRET = "z3cbe87d455a349f8fb0b6efedee5kf9skdlklsd4554ew5f45w45f45efewfesakalklsklasklaksalkaslsklewkdlweklwelkwe";


    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATION_TIME = 864_000_000;

    private void SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
