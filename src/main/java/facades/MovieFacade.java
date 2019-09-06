package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.ArrayList;
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
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getAllMoviesCount() {
        EntityManager em = emf.createEntityManager();
        try {
            Long movies = (long) em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return movies;
        } finally {
            em.close();
        }
    }

    //Returns a list of all movie names and the year it was made
    public List<MovieDTO> getListOfMoviesByYear(long year) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MovieDTO> mbyyear
                    = em.createQuery("SELECT new dto.MovieDTO(m) FROM Movie m WHERE m.year = :year", MovieDTO.class).setParameter("year", year);
            return mbyyear.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    //Returns a list of all movies and everything
    public List<MovieDTO> getAllMovies(){
        EntityManager em = emf.createEntityManager();
        try {
            List<Movie> movies = em.createNamedQuery("Movie.getAll").getResultList();
            List<MovieDTO> result = new ArrayList<>();
            movies.forEach((movie) -> {
                result.add(new MovieDTO(movie));
            });
            return result;
        } finally {
            em.close();
        }
    }
    
    //Returns a list of movies by the name
    public MovieDTO getMovieByName(String name) throws Exception{
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT new dto.MovieDTO(m) FROM Movie m WHERE m.name = :name", MovieDTO.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("No movie found by that name");
        } finally {
            em.close();
        }
    }
    
    //Returns a list of all movies made by specific director
    public MovieDTO getListOfMoviesByDirector(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT m FROM Movie m WHERE m.director = :name", MovieDTO.class).setParameter("name", name).getSingleResult();
        } finally {
            em.close();
        }
    }
    
    // Returns movie by id
    public MovieDTO getMovieById(Long id){
        EntityManager em = getEntityManager();
        try{
            Movie movie = em.find(Movie.class, id);
            return new MovieDTO(movie);
        } finally{
            em.close();
        }
    }
    
    //Adds a new movie
    public Movie addMovie(Movie m){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
            return m;
        } finally {
            em.close();
        }
    }
}
