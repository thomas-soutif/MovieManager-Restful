package movie.manager.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import movie.manager.rest.dao.MovieDao;
import movie.manager.rest.exception.GenericExceptionMapper;
import movie.manager.rest.model.Movie;


@Path("/movies")
public class MoviesResource extends GenericExceptionMapper {

	// Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    // Return the list of movies to the user in the browser
    @GET
    @Produces({MediaType.TEXT_XML})
    public List<Movie> getFilmsBrowser() {
        return MovieDao.instance.getAllFilms();
    }
    
    // Return the list of movies for applications
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Movie> getFilms() {
        return MovieDao.instance.getAllFilms();
	}
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newMovie(
                         @FormParam("title") String title,
                         @FormParam("duration") int duration,
                         @FormParam("language") String language,
                         @FormParam("director") String director,
                         @FormParam("actors") String actorsList,
                         @FormParam("minimumAgeRequirement") int minimumAgeRequirement,
                         @FormParam("startDate") String startDate,
                         @FormParam("endDate") String endDate,
                         @FormParam("screeningDays") String screeningDaysList,
                         @FormParam("screeningTime") String screeningTimeList,
                         @Context HttpServletResponse servletResponse) throws IOException {
    	String[] actors = actorsList.split(",");
    	String[] screeningDays = screeningDaysList.split(",");
    	String[] screeningTimes = screeningTimeList.split(",");
    	
    	Movie movie = new Movie(title, duration, language, director,actors, minimumAgeRequirement);
    	movie.setProjectionPeriod(startDate, endDate, screeningDays, screeningTimes);
    	MovieDao.instance.addFilm(movie);
    	servletResponse.sendRedirect("../list_movie.html");
   }
    
    @Path("{movie}")
    public MovieResource getFilm(@PathParam("movie") String titre) {
        return new MovieResource(uriInfo, request, titre);
    }
}