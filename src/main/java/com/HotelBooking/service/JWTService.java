package com.HotelBooking.service;

import com.HotelBooking.entity.PropertyUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JWTService {

    @Value("${jwt.algorithms.key}")
    private String algorithmKey;        //security key

    @Value("${jwt.issue}")
    private String issuer; // taki pata chale token kis ne issue kiya hai

    @Value("${jwt.expiryDuration}")
    private int expiryTime;

    private Algorithm algorithm;    // we can not able to add algorithm directly..because this Algorithm comes from Jwt token library

    private final static String USER_NAME = "username"; // because once for one user that why make it final


    @PostConstruct
    public void postConstruct() // post construct run automatically
    {
        algorithm  =  Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(PropertyUser user)
    {
        // how to generate token we can 4 things apply USER_NAME, expiryTime,issuer and algorithm

       return JWT.create()
               // we can put the user_name in my token for recognition which user of token... because we generate 100 of signin every signin one token
                .withClaim(USER_NAME,user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }


    // How do I code such that after login if I make any request..How will extract the token from the http request
        public String getUsername(String token)
        {
           DecodedJWT decodedjwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token); // this will help me to decode the token.
            return decodedjwt.getClaim(USER_NAME).asString();
        }

}





// withClaim :- claim is put your username in payload.. claim is work as key value pair

// algorithm consist two things.. 1. algorithm 2. security key

/* getClaim :- getclaim is built in method which read the USER_NAME from decoded JWT token and getclaim return type
    is not strinq by appling asString that USER_NAME converted to string
 */


/*  UserService (PropertyUser) :- Because user service generated token method because user service knows whether the username,password
    .because user service know username, password valid or not
    . user service call this token it will supply that object that you getting it from the Database
 */