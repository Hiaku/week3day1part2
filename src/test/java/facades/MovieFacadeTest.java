package facades;

import dto.MovieDTO;
import utils.EMF_Creator;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    public static Movie lion;
    public static List<Movie> movies = new ArrayList<>();

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        facade = MovieFacade.getMovieFacade(emf);
        lion = new Movie(1978, "Lion King", "Lion cup", "Disney", 5);
        movies.add(lion);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNativeQuery("DELETE FROM MOVIE").executeUpdate();
            em.persist(lion);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetMovieByName() throws Exception{
        // What we expect to see
        MovieDTO expected = new MovieDTO(lion);
        // What we get Back
        MovieDTO result = facade.getMovieByName("Lion King");
        // Checks if result and expected are equal
        assertEquals(expected, result);
    }
    
    @Test
    public void testGetAllMoviesCount() throws Exception{
        //
        long expected = 1l;
        //
        long result = facade.getAllMoviesCount();
        //
        assertEquals(expected, result);
    }
    
    @org.junit.jupiter.api.Test
    public void testGetListOfMoviesByDirector(){
        //
        List<MovieDTO> expected = new ArrayList<>();
        expected.add(new MovieDTO(lion));
        System.out.println(expected.toString());
        //
        List<MovieDTO> result = facade.getAllMovies();
        System.out.println(result.toString());
        //
        assertEquals(expected, result);
    }

    @org.junit.jupiter.api.Test
    public void testGetListOfMoviesByYear(){
        //
        List<MovieDTO> expected = new ArrayList<>();
        expected.add(new MovieDTO(lion));
        //
        List<MovieDTO> result = facade.getListOfMoviesByYear(1978);
        //
        assertEquals(expected, result);
    }
    
    @org.junit.jupiter.api.Test
    public void testGetAllMovies(){
        //
        List<MovieDTO> expected = new ArrayList<>();
        expected.add(new MovieDTO(lion));
        //
        List<MovieDTO> result = facade.getAllMovies();
        //
        assertEquals(expected, result);
    }
    
    @org.junit.jupiter.api.Test 
    public void testMakeMovie(){
        //
        Movie lionking = new Movie(1980, "Lion King 2", "Lion", "Disney", 6);
        //
        Movie result = facade.addMovie(lionking);
        //
        assertEquals(lionking, result);
        //
        EntityManager em = emf.createEntityManager();
        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(Movie.class, new Long(movies.size()+1)));
            em.getTransaction().commit();
        } finally{
            em.close();
        }
    }
}
