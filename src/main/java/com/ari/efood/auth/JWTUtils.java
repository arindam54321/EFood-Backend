package com.ari.efood.auth;

import com.ari.efood.enums.Role;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;
import org.jose4j.lang.JoseException;

import java.util.Map;

public class JWTUtils {
    public static final String JWT_HEADER_KEY = "jwt";
    protected static final String JWT_ISSUER = "sender";
    protected static final String JWT_ID_KEY = "id";
    protected static final String JWT_NAME_KEY = "name";
    protected static final String JWT_EMAIL_KEY = "email";
    protected static final String JWT_ROLE_KEY = "role";
    protected static final float JWT_VALID_FOR_DAYS = 7;
    protected static final float JWT_VALID_FOR_HOURS = JWT_VALID_FOR_DAYS * 24;
    protected static final float JWT_VALID_FOR_MINUTES = JWT_VALID_FOR_HOURS * 60;

    public static String generateJWT(String id, String name, String email, Role role) throws JoseException {
        JwtClaims claims = new JwtClaims();
        claims.setSubject(email);
        claims.setIssuedAtToNow();
        claims.setExpirationTimeMinutesInTheFuture(JWT_VALID_FOR_MINUTES);
        claims.setIssuer(JWT_ISSUER);
        claims.setStringClaim(JWT_ID_KEY, id);
        claims.setStringClaim(JWT_NAME_KEY, name);
        claims.setStringClaim(JWT_EMAIL_KEY, email);
        claims.setStringClaim(JWT_ROLE_KEY, role.getRole());

        JsonWebSignature jws = new JsonWebSignature();
        jws.setAlgorithmConstraints(AlgorithmConstraints.NO_CONSTRAINTS);
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.NONE);
        jws.setPayload(claims.toJson());

        String jwt = jws.getCompactSerialization();
        return jwt;
    }

    public static Map<String, Object> parseJWT(String token) throws InvalidJwtException {
        JwtConsumer consumer = new JwtConsumerBuilder()
                .setJwsAlgorithmConstraints(AlgorithmConstraints.NO_CONSTRAINTS)
                .setExpectedIssuer(JWT_ISSUER)
                .setDisableRequireSignature()
                .setRequireExpirationTime()
                .setRequireIssuedAt()
                .build();

        JwtContext context = consumer.process(token);
        JsonWebSignature jws = (JsonWebSignature) context.getJoseObjects().get(0);
        JwtClaims claims = context.getJwtClaims();
        Map<String, Object> jwtMap = claims.getClaimsMap();

        return jwtMap;
    }
}
