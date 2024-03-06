package movie.manager.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import movie.manager.rest.model.Movie;

public enum MovieDao {
    instance;
	
    private Map<String, Movie> contentProvider = new HashMap<>();

    private MovieDao() {
        // Initialisation des films (à titre d'exemple)
    	Movie film1 = new Movie("Interstellar", "Interstellar", 169, "Anglais", "Christopher Nolan", 
                                new String[]{"Matthew McConaughey", "Anne Hathaway"}, 12);
        film1.setProjectionPeriod("2024-03-01", "2024-03-31", new String[]{"Lundi", "Mercredi", "Vendredi"}, "19:00");
        contentProvider.put("Interstellar", film1);

        Movie film2 = new Movie("Inception", "Inception", 148, "Anglais", "Christopher Nolan", 
                                new String[]{"Leonardo DiCaprio", "Ellen Page"}, 12);
        film2.setProjectionPeriod("2024-03-01", "2024-03-31", new String[]{"Mardi"},"15:00");
        contentProvider.put("Inception", film2);
        
    }
    
    public Map<String, Movie> getModel(){
        return contentProvider;
    }
    
    public Movie getFilm(String title) {
        for (Movie movie : contentProvider.values()) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null; // Retourne null si aucun film avec le titre spécifié n'est trouvé
    }
    
    public void addFilm(Movie movie) {
        contentProvider.put(movie.getTitle(), movie);
    }
    
    public List<Movie> getAllFilms() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.addAll(instance.getModel().values());
        return movies;
    }

    
}
