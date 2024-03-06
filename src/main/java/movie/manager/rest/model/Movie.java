package movie.manager.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Movie {
    private String id;
    private String title;
    private int duration; // Durée du film en minutes
    private String language;
    private String director;
    private String[] actors;
    private int minimumAgeRequirement;
    private String startDate;
    private String endDate;
    private String[] screeningDays;
    private String screeningTime;

    public Movie() {
        // Constructeur par défaut nécessaire pour JAXB
    }

    public Movie(String id, String title, int duration, String language, String director, String[] actors, int minimumAgeRequirement) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.language = language;
        this.director = director;
        this.actors = actors;
        this.minimumAgeRequirement = minimumAgeRequirement;
    }

    public void setProjectionPeriod(String startDate, String endDate, String[] screeningDays, String screeningTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.screeningDays = screeningDays;
        this.screeningTime = screeningTime;
    }
    
    // Getter et setter
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public int getMinimumAgeRequirement() {
        return minimumAgeRequirement;
    }

    public void setMinimumAgeRequirement(int minimumAgeRequirement) {
        this.minimumAgeRequirement = minimumAgeRequirement;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String[] getScreeningDays() {
        return screeningDays;
    }

    public void setScreeningDays(String[] screeningDays) {
        this.screeningDays = screeningDays;
    }

    public String getScreeningTime() {
        return screeningTime;
    }

    public void setScreeningTime(String screeningTime) {
        this.screeningTime = screeningTime;
    }


}
