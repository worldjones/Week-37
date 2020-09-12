package facades;

import utils.EMF_Creator;
import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    Movie titanic;
    Movie fastAndFurious;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            titanic = facade.createMovie("Titanic", "James Cameron", 1997, "Leonardo DiCaprio", "Kate Winslet");
            fastAndFurious = facade.createMovie("The Fast and the Furious", "Rob Cohen", 2001, "Paul Walker / Vin Diesel", "Jordana Brewster");
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(titanic);
            em.persist(fastAndFurious);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(2, facade.getMovieCount(), "Expects two rows in the database");
    }

    @Test
    public void testGetMovies() {
        assertEquals(2, facade.getMovies().size(), "Expects two movies in the database");
    }

    @Test
    public void testGetMoviesByTitle() {
        List<Movie> movies = facade.getMoviesByTitle(titanic.getTitle());
        assertEquals(titanic.getId(), movies.get(0).getId(), "Expects the same id");
    }
    
    @Test
    public void testGetMoviesByDirector() {
        List<Movie> movies = facade.getMoviesByDirector(fastAndFurious.getDirector());
        assertEquals(fastAndFurious.getId(), movies.get(0).getId(), "Expects the same id");
    }
    
    @Test
    public void testGetMoviesByYear() {
        List<Movie> movies = facade.getMoviesByYear(titanic.getYear());
        assertEquals(titanic.getId(), movies.get(0).getId(), "Expects the same id");
    }
    
    @Test
    public void testGetMovieById() {
        Movie movie = facade.getMovieById(fastAndFurious.getId());
        assertEquals(fastAndFurious.getTitle(), movie.getTitle(), "Expects the same title");
    }

}
