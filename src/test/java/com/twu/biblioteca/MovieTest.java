package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    @Test
    void shouldBeAbleToReturnAllDetailsOfMovieX() {
        Movie movie = new Movie("X", 1898, "Y", 1);

        String expectedMovieDetail = "X | 1898 | Y | 1";

        assertEquals(expectedMovieDetail, movie.columnedProperties());
    }

    @Test
    void shouldBeAbleToReturnAllDetailsOfMovieY() {
        Movie movie = new Movie("Y", 1898, "X", 1);

        String expectedMovieDetail = "Y | 1898 | X | 1";

        assertEquals(expectedMovieDetail, movie.columnedProperties());
    }

    @Test
    void shouldReturnTrueIfTheMovieNameMatches() {
        Movie movie = new Movie("Y", 1898, "X", 1);

        assertTrue(movie.isName("Y"));
    }

    @Test
    void shouldReturnFalseIfTheMovieNameNotMatches() {
        Movie movie = new Movie("Y", 1898, "X", 1);

        assertFalse(movie.isName("X"));
    }

    @Test
    void shouldReturnTheMovieSignatureAsMovie() {
        Movie movie = new Movie("X", 1898, "Y", 1);

        assertEquals(Signature.MOVIE, movie.signature());
    }
}