package movie.manager.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import movie.manager.rest.dao.MovieDao;
import movie.manager.rest.model.Movie;

public class MovieResource {
	@Context
    UriInfo uriInfo;
    @Context
    Request request;
    String title;
   
    public MovieResource(UriInfo uriInfo, Request request, String title) {
    	this.uriInfo = uriInfo;
        this.request = request;
        this.title = title;
    }

  //Application integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Movie getFilm() {
        Movie movie = MovieDao.instance.getModel().get(title);
        if(movie ==null)
            throw new RuntimeException("Get: Todo with " + title +  " not found");
        return movie;
    }
    
    // for the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public Movie getFilmHTML() {
        Movie movie = MovieDao.instance.getModel().get(title);
        if(movie ==null)
            throw new RuntimeException("Get: Todo with " + title +  " not found");
        return movie;
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putFilm(JAXBElement<Movie> movie) {
        Movie c = movie.getValue();
        return putAndGetResponse(c);
    }
    
    @DELETE
    public void deleteFilm() {
        Movie c = MovieDao.instance.getModel().remove(title);
        if(c==null)
            throw new RuntimeException("Delete: Todo with " + title +  " not found");
    }

    private Response putAndGetResponse(Movie movie) {
        Response res;
        if (MovieDao.instance.getModel().containsKey(movie.getTitle())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        MovieDao.instance.getModel().put(movie.getTitle(), movie);
        return res;
    }
}
