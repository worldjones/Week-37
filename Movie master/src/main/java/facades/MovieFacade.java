package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Movie createMovie(String title, String director, int year, String mainMaleActor, String mainFemaleActor) {
        EntityManager em = emf.createEntityManager();
        Movie movie = new Movie(title, director, year, mainMaleActor, mainFemaleActor);
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return movie;
        } finally {
            em.close();
        }
    }
    
    public List<Movie> getMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query =
                    em.createQuery("SELECT m FROM Movie m", Movie.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Movie> getMoviesByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query =
                    em.createQuery("SELECT m FROM Movie m WHERE m.title = :title", Movie.class);
            query.setParameter("title", title);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Movie> getMoviesByDirector(String director) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query =
                    em.createQuery("SELECT m FROM Movie m WHERE m.director = :director", Movie.class);
            query.setParameter("director", director);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Movie> getMoviesByYear(int year) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query =
                    em.createQuery("SELECT m FROM Movie m WHERE m.year = :year", Movie.class);
            query.setParameter("year", year);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Movie getMovieById (long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Movie movie = em.find(Movie.class, id);
            return movie;
        } finally {
            em.close();
        }
    }
    
    //TODO Remove/Change this before use
    public long getMovieCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long movieCount = (long)em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return movieCount;
        }finally{  
            em.close();
        }
        
    }

}
