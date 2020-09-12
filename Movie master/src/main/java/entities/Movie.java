package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Movie.deleteAllRows", query = "DELETE from Movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private int year;
    private String mainMaleActor;
    private String mainFemaleActor;

    public Movie(String title, String director, int year, String mainMaleActor, String mainFemaleActor) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.mainMaleActor = mainMaleActor;
        this.mainFemaleActor = mainFemaleActor;
    }
    
    
    
    public Movie() {
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMainMaleActor() {
        return mainMaleActor;
    }

    public void setMainMaleActor(String mainMaleActor) {
        this.mainMaleActor = mainMaleActor;
    }

    public String getMainFemaleActor() {
        return mainFemaleActor;
    }

    public void setMainFemaleActor(String mainFemaleActor) {
        this.mainFemaleActor = mainFemaleActor;
    }
    
    
    
    
    

   
}
