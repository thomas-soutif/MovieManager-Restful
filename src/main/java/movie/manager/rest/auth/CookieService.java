package movie.manager.rest.auth;

import movie.manager.rest.dao.UserDao;
import movie.manager.rest.model.User;

public class CookieService {
	
	private static String cookieName = "movie.manager.Cookie";
	
	public static User getUserFromCookie(String cookie) {
		  
		    if (cookie != null) {
		        User user = UserDao.instance.getUserFromCookie(cookie);
		        return user;
		    }
		    return null;

	}
}
