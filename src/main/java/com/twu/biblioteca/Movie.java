package com.twu.biblioteca;

public class Movie implements LibraryItem {

    private final String name;
    private final int year;
    private final String director;
    private final int rating;

    public Movie(String name, int year, String director, int rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    @Override
    public String columnedProperties() {
        return name + " | " + year + " | " + director + " | " + rating;
    }

    @Override
    public boolean isName(String checkName) {
        return name.equals(checkName);
    }

    @Override
    public Signature signature() {
        return Signature.MOVIE;
    }
}
