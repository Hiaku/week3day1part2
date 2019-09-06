package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
@NamedQuery(name = "Movie.deleteAllRows", query = "DELETE from Movie"),
@NamedQuery(name = "Movie.getAll", query = "SELECT m FROM Movie m"),
})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String name;
    private String actors;
    private String director;
    private long stock;
    
    public Movie() {
    }

    public Movie(int year, String name, String actors, String director, long stock) {
        this.year = year;
        this.name = name;
        this.actors = actors;
        this.director = director;
        this.stock = stock;
    }

//    public Movie(int year, String name) {
//        this.year = year;
//        this.name = name;
//    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }
    
    // TODO, delete this class, or rename to an Entity class that makes sense for what you are about to do
    // Delete EVERYTHING below if you decide to use this class, it's dummy data used for the initial demo
//    private String dummyStr1;
//    private String dummyStr2;
//
//    public Movie(String dummyStr1, String dummyStr2) {
//        this.dummyStr1 = dummyStr1;
//        this.dummyStr2 = dummyStr2;
//    }
//
//    public String getDummyStr1() {
//        return dummyStr1;
//    }
//
//    public void setDummyStr1(String dummyStr1) {
//        this.dummyStr1 = dummyStr1;
//    }
//
//    public String getDummyStr2() {
//        return dummyStr2;
//    }
//
//    public void setDummyStr2(String dummyStr2) {
//        this.dummyStr2 = dummyStr2;
//    }
    
    
    

   
}
