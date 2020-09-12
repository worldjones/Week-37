package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movies")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final MovieFacade FACADE = MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<Movie> movies = FACADE.getMovies();
        List<MovieDTO> movieDTOs = new ArrayList();
        for (Movie movie : movies) {
            MovieDTO movieDTO = new MovieDTO(movie);
            movieDTOs.add(movieDTO);
        }
        return GSON.toJson(movieDTOs);
    }

    @Path("title/{title}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMoviesByTitle(@PathParam("title") String title) {
        try {
            List<Movie> movies = FACADE.getMoviesByTitle(title);
            List<MovieDTO> movieDTOs = new ArrayList();
            for (Movie movie : movies) {
                MovieDTO movieDTO = new MovieDTO(movie);
                movieDTOs.add(movieDTO);
            }
            return GSON.toJson(movieDTOs);
        } catch (NoResultException | NullPointerException e) {
            return GSON.toJson(null);
        }
    }
    
    @Path("director/{director}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMoviesByDirector(@PathParam("director") String director) {
        try {
            List<Movie> movies = FACADE.getMoviesByDirector(director);
            List<MovieDTO> movieDTOs = new ArrayList();
            for (Movie movie : movies) {
                MovieDTO movieDTO = new MovieDTO(movie);
                movieDTOs.add(movieDTO);
            }
            return GSON.toJson(movieDTOs);
        } catch (NoResultException | NullPointerException e) {
            return GSON.toJson(null);
        }
    }
    
    @Path("year/{year}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMoviesByYear(@PathParam("year") int year) {
        try {
            List<Movie> movies = FACADE.getMoviesByYear(year);
            List<MovieDTO> movieDTOs = new ArrayList();
            for (Movie movie : movies) {
                MovieDTO movieDTO = new MovieDTO(movie);
                movieDTOs.add(movieDTO);
            }
            return GSON.toJson(movieDTOs);
        } catch (NoResultException | NullPointerException e) {
            return GSON.toJson(null);
        }
    }
    
    @Path("id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieById(@PathParam("id") long id) {
        try {
            Movie movie = FACADE.getMovieById(id);
            MovieDTO movieDTO = new MovieDTO(movie);
            return GSON.toJson(movieDTO);
        } catch (NoResultException | NullPointerException e) {
            return GSON.toJson(null);
        }
    }
}
