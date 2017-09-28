package GUIProgram;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class Movie implements ActionListener, Serializable {
    private String name;
    private String genre;
    private int year;

    public Movie(String name, String genre, int year){
        this.name = name;
        this.genre = genre;
        this.year = year;
    }

    public String getMovie() {
        return getName();
    }

    public void setMovie(String movie) {
        this.setName(movie);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return  "Movie: " + getMovie() +
                " Genre: " + getGenre() +
                " Year: " + getYear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


