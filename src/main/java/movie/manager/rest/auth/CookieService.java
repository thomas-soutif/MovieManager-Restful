package movie.manager.rest.auth;

import java.io.Console;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import movie.manager.rest.dao.UserDao;
import movie.manager.rest.model.User;

public class CookieService {
	
	private String cookieName = "movie.manager.Cookie";
	
	public static User getUserFromCookie(HttpServletRequest request) {
		 Cookie[] cookies = request.getCookies();
		    String userCookie = null;

		    // Recherche du cookie utilisateur
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if ("movie.manager.Cookie".equals(cookie.getName())) {
		                userCookie = cookie.getValue();
		                break;
		            }
		        }
		    }
		    
		    if (userCookie != null) {
		        // Construire l'utilisateur associé au cookie (vous devrez implémenter cette partie)
		        User user = UserDao.instance.getUserFromCookie(userCookie);
		        return user;
		    }
		    return null;

	}
}
