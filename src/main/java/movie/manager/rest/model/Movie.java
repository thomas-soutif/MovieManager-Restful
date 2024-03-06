package movie.manager.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Movie {
    private String title;
    private int duration; // Durée du film en minutes
    private String language;
    private String director;
    private String[] actors;
    private int minimumAgeRequirement;
    private String startDate;
    private String endDate;
    private String[] screeningDays;
    private String[] screeningTimes;

    public Movie() {
        // Constructeur par défaut nécessaire pour JAXB
    }

    public Movie(String title, int duration, String language, String director, String[] actors, int minimumAgeRequirement) {
        this.title = title;
        this.duration = duration;
        this.language = language;
        this.director = director;
        this.actors = actors;
        this.minimumAgeRequirement = minimumAgeRequirement;
    }

    public void setProjectionPeriod(String startDate, String endDate, String[] screeningDays, String[] screeningTimes) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.screeningDays = screeningDays;
        this.screeningTimes = screeningTimes;
    }
    
    // -------------- Actors Wrapper --------------------- //
    @XmlElement
    public ActorsWrapper getActors() {
        ActorsWrapper wrapper = new ActorsWrapper();
        wrapper.setActors(actors);
        return wrapper;
    }
    
    public static class ActorsWrapper {
        private String[] actors;

        @XmlElement(name = "actor")
        public String[] getActors() {
            return actors;
        }

        public void setActors(String[] actors) {
            this.actors = actors;
        }
    }
    
    // -------------- Screening Days Wrapper --------------------- //
    @XmlElement
    public ScreeningDaysWrapper getScreeningDays() {
    	ScreeningDaysWrapper wrapper = new ScreeningDaysWrapper();
        wrapper.setScreeningDays(screeningDays);
        return wrapper;
    }
    
    public static class ScreeningDaysWrapper {
        private String[] screeningDays;

        @XmlElement(name = "ScreeningDay")
        public String[] getScreeningDays() {
            return screeningDays;
        }

        public void setScreeningDays(String[] screeningDays) {
            this.screeningDays = screeningDays;
        }
    }
    
 // -------------- Screening Time Wrapper --------------------- //
    @XmlElement
    public ScreeningTimesWrapper getScreeningTimes() {
    	ScreeningTimesWrapper wrapper = new ScreeningTimesWrapper();
        wrapper.setScreeningTimes(screeningTimes);
        return wrapper;
    }
    
    public static class ScreeningTimesWrapper {
        private String[] screeningTimes;

        @XmlElement(name = "ScreeningTime")
        public String[] getScreeningTimes() {
            return screeningTimes;
        }

        public void setScreeningTimes(String[] screeningTimes) {
            this.screeningTimes = screeningTimes;
        }
    }
    
    // Getter et setter
    
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

    public void setScreeningDays(String[] screeningDays) {
        this.screeningDays = screeningDays;
    }


}
