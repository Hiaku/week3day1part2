package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final MovieFacade FACADE =  MovieFacade.getMovieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @Path("/test")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String hello() {
        return "{\"hello\":\"world\"}";
    }
    
    @Path("/count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMoviesCount() {
        long count = FACADE.getAllMoviesCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @GET
    @Path("/year/{year}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getListOfMoviesByYear() {
        List<MovieDTO> movies = FACADE.getAllMovies();
        return GSON.toJson(movies);
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<MovieDTO> movies = FACADE.getAllMovies();
        return GSON.toJson(movies);
    }
    
    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByName(@PathParam("name") String name) {
        try {
            return GSON.toJson(FACADE.getMovieByName(name));
        } catch (Exception ex) {
            return "{\"errormessage\":\"Movie does not exist\"}";
        }
    }
    
    @GET
    @Path("/director/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getListOfMoviesByDirector() {
        List<MovieDTO> movies = FACADE.getAllMovies();
        return GSON.toJson(movies);
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieById(@PathParam("id") int id){
        return GSON.toJson(FACADE.getMovieById(new Long(id)));
    }
}