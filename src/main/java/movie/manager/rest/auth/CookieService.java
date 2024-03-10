package movie.manager.rest.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import movie.manager.rest.dao.UserDao;
import movie.manager.rest.model.User;

public class CookieService {
	
	private static String cookieName = "movie.manager.Cookie";
	
	public static User getUserFromCookie(HttpServletRequest request) {
		 Cookie[] cookies = request.getCookies();
		    String userCookie = null;

		    // Recherche du cookie utilisateur
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if (cookieName.equals(cookie.getName())) {
		                userCookie = cookie.getValue();
		                break;
		            }
		        }
		    }
		    
		    if (userCookie != null) {
		        User user = UserDao.instance.getUserFromCookie(userCookie);
		        return user;
		    }
		    return null;

	}
}
