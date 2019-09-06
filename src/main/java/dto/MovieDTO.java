/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;
import java.util.Objects;


/**
 *
 * @author Nikolaj
 */
public class MovieDTO {
    private Long id;
    private int year;
    private String name;
    private String actors;
    private String director;
    private long left;

    public MovieDTO() {
    }
    
    public MovieDTO(Movie movie){
        this.id = movie.getId();
        this.year = movie.getYear();
        this.name = movie.getName();
        this.actors = movie.getActors();
        this.director = movie.getDirector();
        this.left = movie.getStock();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.year;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.actors);
        hash = 79 * hash + Objects.hashCode(this.director);
        hash = 79 * hash + (int) (this.left ^ (this.left >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MovieDTO other = (MovieDTO) obj;
        if (this.year != other.year) {
            return false;
        }
        if (this.left != other.left) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.actors, other.actors)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public long getLeft() {
        return left;
    }

    public void setLeft(long left) {
        this.left = left;
    }
    
    
}
