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
    	
    	addMovie("Interstellar", 169, "Anglais", "Christopher Nolan", new String[]{"Matthew McConaughey", "Anne Hathaway"}, 12,
                "2024-03-01", "2024-03-31", new String[]{"Lundi", "Mercredi", "Vendredi"}, new String[]{"19:00"});

        addMovie("Inception", 148, "Anglais", "Christopher Nolan", new String[]{"Leonardo DiCaprio", "Ellen Page"}, 12,
                "2024-03-01", "2024-03-31", new String[]{"Mardi"}, new String[]{"15:00", "16:00"});

        // Ajout de 20 autres films
        addMovie("The Dark Knight", 152, "Anglais", "Christopher Nolan", new String[]{"Christian Bale", "Heath Ledger"}, 12,
                "2024-03-01", "2024-03-31", new String[]{"Lundi", "Mercredi", "Vendredi"}, new String[]{"21:00"});

        addMovie("The Matrix", 136, "Anglais", "Lana Wachowski, Lilly Wachowski", new String[]{"Keanu Reeves", "Laurence Fishburne"}, 12,
                "2024-03-01", "2024-03-31", new String[]{"Mardi", "Jeudi", "Samedi"}, new String[]{"20:00"});
        
        addMovie("Pulp Fiction", 154, "Anglais", "Quentin Tarantino", new String[]{"John Travolta", "Uma Thurman"}, 16,
                "2024-03-01", "2024-03-31", new String[]{"Dimanche", "Mercredi", "Vendredi"}, new String[]{"22:00"});

        addMovie("The Shawshank Redemption", 142, "Anglais", "Frank Darabont", new String[]{"Tim Robbins", "Morgan Freeman"}, 16,
                "2024-03-01", "2024-03-31", new String[]{"Mardi", "Jeudi", "Samedi"}, new String[]{"18:00"});

        addMovie("The Lord of the Rings: The Fellowship of the Ring", 178, "Anglais", "Peter Jackson", new String[]{"Elijah Wood", "Ian McKellen"}, 12,
                "2024-03-01", "2024-03-31", new String[]{"Lundi", "Mardi", "Samedi"}, new String[]{"17:00"});

        addMovie("Forrest Gump", 142, "Anglais", "Robert Zemeckis", new String[]{"Tom Hanks", "Robin Wright"}, 12,
                "2024-03-01", "2024-03-31", new String[]{"Mercredi", "Vendredi", "Dimanche"}, new String[]{"14:00"});

        addMovie("The Godfather", 175, "Anglais", "Francis Ford Coppola", new String[]{"Marlon Brando", "Al Pacino"}, 16,
                "2024-03-01", "2024-03-31", new String[]{"Lundi", "Mercredi", "Samedi"}, new String[]{"19:00"});
        
    }
    
    
    private void addMovie(String title, int duration, String language, String director, String[] actors, int minimumAgeRequirement,
            String startDate, String endDate, String[] screeningDays, String[] screeningTimes) {
		Movie movie = new Movie(title, duration, language, director, actors, minimumAgeRequirement);
		movie.setProjectionPeriod(startDate, endDate, screeningDays, screeningTimes);
		contentProvider.put(movie.getTitle(), movie);
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
