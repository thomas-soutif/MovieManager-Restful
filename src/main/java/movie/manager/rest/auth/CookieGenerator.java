package movie.manager.rest.auth;

import java.security.SecureRandom;
import java.math.BigInteger;

public class CookieGenerator {
    private static final int COOKIE_LENGTH = 32;

    public static String generateUniqueCookie() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[COOKIE_LENGTH];
        random.nextBytes(bytes);
        return new BigInteger(1, bytes).toString(16); // Convertit en représentation hexadécimale
    }
}
