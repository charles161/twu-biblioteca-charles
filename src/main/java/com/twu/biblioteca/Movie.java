package com.twu.biblioteca;

public class Movie extends LibraryItem {

    private final String name;
    private final int year;
    private final String director;
    private final int rating;

    public Movie(String name, int year, String director, int rating) {
        super(Signature.MOVIE);
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

}
