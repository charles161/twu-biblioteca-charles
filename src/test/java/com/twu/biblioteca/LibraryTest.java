package com.twu.biblioteca;

import com.twu.biblioteca.Exceptions.LibraryItemNotAvailableException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {
    @Test
    void shouldReturnTrueIfBooksAreAvailable() {
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);

        Library library = new Library(new ArrayList<>(Arrays.asList(book1, book2)));

        assertTrue(library.isAvailable(book2));
        assertTrue(library.isAvailable(book1));
    }

    @Test
    void shouldReturnFalseIfBooksAreNotAvailable() {
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);

        Library library = new Library(new ArrayList<>());

        assertFalse(library.isAvailable(book2));
        assertFalse(library.isAvailable(book1));
    }

    @Test
    void shouldBeAbleToCheckoutBookByName() throws LibraryItemNotAvailableException {
        Book book1 = mock(Book.class);
        Library library = new Library(new ArrayList<>(Collections.singletonList(book1)));
        String bookName = "someName";
        when(book1.isName(bookName)).thenReturn(true);
        when(book1.signature()).thenReturn(Signature.BOOK);

        library.checkout(bookName, Signature.BOOK);

        assertFalse(library.isAvailable(book1));
    }

    @Test
    void shouldReturnAListOfBookDetails() {
        Book book1 = new Book("Sherlock Holmes", "Sir Arthur Conan Doyle", 1999);
        Library library = new Library(new ArrayList<>(Collections.singleton(book1)));
        String bookDetail = "Sherlock Holmes | Sir Arthur Conan Doyle | 1999";
        List<String> expectedBookDetails = new ArrayList<>();
        expectedBookDetails.add(bookDetail);

        assertEquals(expectedBookDetails, library.itemDetails(Signature.BOOK));
    }

    @Test
    void shouldThrowExceptionWhenCheckingOutABookByNameThatIsUnavailable() {
        Book book1 = mock(Book.class);
        Library library = new Library(new ArrayList<>());
        String bookName = "someName";
        when(book1.isName(bookName)).thenReturn(true);

        assertFalse(library.isAvailable(book1));
        assertThrows(LibraryItemNotAvailableException.class, () -> library.checkout(bookName, Signature.BOOK));
    }

    @Test
    void shouldBeAbleToReturnBookByNameSoThatItsAvailable() throws LibraryItemNotAvailableException {
        Book book1 = mock(Book.class);
        Library library = new Library(new ArrayList<>(Collections.singletonList(book1)));
        String bookName = "someName";
        when(book1.isName(bookName)).thenReturn(true);
        when(book1.signature()).thenReturn(Signature.BOOK);

        library.checkout(bookName, Signature.BOOK);
        library.returnLibraryItem(bookName);

        assertTrue(library.isAvailable(book1));
    }

    @Test
    void shouldThrowExceptionWhenBookReturnedTwice() throws LibraryItemNotAvailableException {
        Book book1 = mock(Book.class);
        Library library = new Library(new ArrayList<>(Collections.singletonList(book1)));
        String bookName = "someName";
        when(book1.isName(bookName)).thenReturn(true);
        when(book1.signature()).thenReturn(Signature.BOOK);

        library.checkout(bookName, Signature.BOOK);
        library.returnLibraryItem(bookName);

        assertThrows(LibraryItemNotAvailableException.class, () -> library.returnLibraryItem(bookName));

    }

    @Test
    void shouldReturnAListOfMovieDetails() {
        Movie movie = new Movie("X", 1898, "Y", 1);
        Book book = new Book("Sherlock Holmes", "Sir Arthur Conan Doyle", 1999);

        Library library = new Library(new ArrayList<>(Arrays.asList(movie, book)));
        String movieDetail = "X | 1898 | Y | 1";

        List<String> expectedBookDetails = new ArrayList<>();
        expectedBookDetails.add(movieDetail);

        assertEquals(expectedBookDetails, library.itemDetails(Signature.MOVIE));
    }

    @Test
    void shouldBeAbleToCheckoutMovieByName() throws LibraryItemNotAvailableException {
        Movie movie = mock(Movie.class);
        Library library = new Library(new ArrayList<>(Collections.singletonList(movie)));
        String movieName = "someName";
        when(movie.isName(movieName)).thenReturn(true);
        when(movie.signature()).thenReturn(Signature.MOVIE);

        library.checkout(movieName, Signature.MOVIE);

        assertFalse(library.isAvailable(movie));
    }

}
