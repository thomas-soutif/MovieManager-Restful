package movie.manager.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.json.Json;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import movie.manager.rest.auth.CookieGenerator;
import movie.manager.rest.auth.CookieService;
import movie.manager.rest.dao.UserDao;
import movie.manager.rest.exception.GenericExceptionMapper;
import movie.manager.rest.model.User;
import movie.manager.rest.model.UsersListWrapper;

@Path("/users")
public class UsersResource extends GenericExceptionMapper {
	
	// Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    // Return the list of users to the user in the browser
    @GET
    @Produces({MediaType.TEXT_XML})
    public Response getUsersBrowser(
    		@Context HttpServletRequest servletRequest) {
        
    	User user = CookieService.getUserFromCookie(servletRequest);
    	if (user == null || user.getRole() != User.Role.STAFF) {
            // Pas les droits nécessaires
        	return Response.status(Response.Status.FORBIDDEN).build();
        }
    	UsersListWrapper usersWrapper = new UsersListWrapper(UserDao.instance.getAllUsers());
    	return Response.ok(usersWrapper).build();
    }
    
    // Return the list of movies for applications
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response getUsers(@Context HttpServletRequest servletRequest) {

    	User user = CookieService.getUserFromCookie(servletRequest);
    	if (user == null || user.getRole() != User.Role.STAFF) {
            // Pas les droits nécessaires
        	return Response.status(Response.Status.FORBIDDEN).build();
        }
    	UsersListWrapper usersWrapper = new UsersListWrapper(UserDao.instance.getAllUsers());
    	return Response.ok(usersWrapper).build();
	}
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newUser(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("role") String role,
            @Context HttpServletResponse servletResponse) throws IOException {

        if (UserDao.instance.userExists(username)) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User(username, password);

        if ("staff".equalsIgnoreCase(role)) {
            user.setRole(User.Role.STAFF);
        } else if ("provider".equalsIgnoreCase(role)) {
            user.setRole(User.Role.PROVIDER);
        } else if ("member".equalsIgnoreCase(role)) {
            user.setRole(User.Role.MEMBER);
        } else {
            throw new RuntimeException("Invalid user role");
        }

        UserDao.instance.addUser(user);

        servletResponse.sendRedirect("../login.html");
    }
    
    @GET
    @Path("is_login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response isLoginUser(
    		@Context HttpServletRequest servletRequest,
            @Context HttpServletResponse servletResponse) throws IOException {
    		
        	User user = CookieService.getUserFromCookie(servletRequest);
        	ObjectMapper objectMapper = new ObjectMapper();
        	Map<String, Object> jsonResponse = new HashMap<>();
            if (user == null || !user.isConnected()) {
                // Pas connecté
            	jsonResponse.put("connected", false);
            } else {
                // Connecté
            	jsonResponse.put("connected", true);
            	jsonResponse.put("username", user.getUsername());
            }
            String json = objectMapper.writeValueAsString(jsonResponse);
            return Response.ok(json).build();
    }
    
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response loginUser(
    		@FormParam("username") String username,
            @FormParam("password") String password,
            @Context HttpServletResponse servletResponse) throws IOException {
        
    	User user = UserDao.instance.getUser(username);

        if (user != null && user.getPassword().equals(password)) {
            // Générez un cookie unique
            String cookie = CookieGenerator.generateUniqueCookie();

            // Stockez le cookie dans l'objet User
            user.setCookie(cookie.toString());

            // Enregistrez le cookie dans le navigateur de l'utilisateur
            Cookie cookie_obj = new Cookie("movie.manager.Cookie", cookie.toString());
            //servletResponse.addCookie(cookie_obj);
            //NewCookie newCookie = new NewCookie("movie.manager.Cookie", cookie.toString());
            NewCookie newCookie = new NewCookie("movie.manager.Cookie", cookie.toString(), "/", null, NewCookie.DEFAULT_VERSION, null, NewCookie.DEFAULT_MAX_AGE, null, false, true);
            return Response.ok().cookie(newCookie).build();

            // Redirigez l'utilisateur vers une page sécurisée
            //return Response.ok().cookie(newCookie).build();
            
        } else {
            // Gestion des erreurs de connexion
    	    return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("disconnect")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void disconnectUser(
    		@Context HttpServletRequest servletRequest,
            @Context HttpServletResponse servletResponse) throws IOException {
    		
        	User user = CookieService.getUserFromCookie(servletRequest);
            if (user != null && user.isConnected()) {
               user.disconnect();
            }
            servletResponse.sendRedirect("/movie.manager/");
    }
    
    @Path("{user}")
    public UserResource getUser(@PathParam("user") String username) {
        return new UserResource(uriInfo, request, username);
    }

}
