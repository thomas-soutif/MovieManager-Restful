package movie.manager.rest.auth;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import movie.manager.rest.dao.UserDao;
import movie.manager.rest.model.User;

import java.math.BigInteger;



public class JWTAuthentification {
	// Clé secrète pour signer les JWT (à remplacer par une clé plus sécurisée en production)
	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public static User getUserFromRequestHeader(HttpHeaders headers) {
		String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            String jwt = authorizationHeader.substring(7); // Supprimer le préfixe "Bearer "

	            // Valider le JWT
	            try {
	                Claims claims = Jwts.parserBuilder()
	                        .setSigningKey(SECRET_KEY) // Utiliser la clé secrète pour valider le JWT
	                        .build()
	                        .parseClaimsJws(jwt)
	                        .getBody();

	                // Utiliser les informations du JWT pour vérifier si l'utilisateur est connecté (grâce à un cookie unique).
	                String cookie = claims.getSubject();
	                if (cookie != null) {
	                	User user = CookieService.getUserFromCookie(cookie);
	                	return user;
	                }
	            }
                catch (Exception e) {
                	return null;
                }
                return null;

		 }
		return null;
	}
	
	public static String createATokenForTheUser(User user) {
		//Le but ici est de générer un identifiant unique pour l'utilisateur, afin de l'associer avec le token JWT.
		String new_cookie = CookieGenerator.generateUniqueCookie();
		// Stocke le cookie dans l'objet User
        user.setCookie(new_cookie.toString());
     
    	// Générer le JWT avec les informations de l'utilisateur (via son cookie).
        String jwt = Jwts.builder()
                .setSubject(user.getCookie())
                .claim("role", user.getRole().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(3600))) // Expiration dans 1 heure
                .signWith(JWTAuthentification.getSecret())
                .compact();
        
		
		return jwt;
	}
	
	
	
	public static SecretKey getSecret() {
		return SECRET_KEY;
	}

	}
