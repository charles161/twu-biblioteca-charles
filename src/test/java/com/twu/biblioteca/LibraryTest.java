package com.twu.biblioteca;

import com.twu.biblioteca.Exceptions.BookAlreadyReturnedException;
import com.twu.biblioteca.Exceptions.BookNotAvailableException;
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
    void shouldBeAbleToCheckoutABookIfItIsAvailable() throws BookNotAvailableException {
        Book book1 = mock(Book.class);

        Library library = new Library(new ArrayList<>(Collections.singletonList(book1)));

        assertTrue(library.isAvailable(book1));
        library.checkout(book1);
        assertFalse(library.isAvailable(book1));
    }

    @Test
    void shouldThrowExceptionWhenCheckingOutABookThatIsUnavailable() {
        Book book1 = mock(Book.class);

        Library library = new Library(new ArrayList<>());

        assertFalse(library.isAvailable(book1));
        assertThrows(BookNotAvailableException.class, () -> library.checkout(book1));
    }

    @Test
    void shouldBeAbleToCheckoutBookByName() throws BookNotAvailableException {
        Book book1 = mock(Book.class);
        Library library = new Library(new ArrayList<>(Arrays.asList(book1)));
        String bookName = "someName";
        when(book1.isName(bookName)).thenReturn(true);

        library.checkout(bookName);

        assertFalse(library.isAvailable(book1));
    }

    @Test
    void shouldReturnAListOfBookDetails() {
        Book book1 = new Book("Sherlock Holmes", "Sir Arthur Conan Doyle", 1999);
        Library library = new Library(new ArrayList<>(Collections.singleton(book1)));
        String bookDetail = "Sherlock Holmes | Sir Arthur Conan Doyle | 1999";
        List<String> expectedBookDetails = new ArrayList<>();
        expectedBookDetails.add(bookDetail);

        assertEquals(expectedBookDetails, library.availableBooksDetail());
    }

    @Test
    void shouldThrowExceptionWhenCheckingOutABookByNameThatIsUnavailable() {
        Book book1 = mock(Book.class);
        Library library = new Library(new ArrayList<>());
        String bookName = "someName";
        when(book1.isName(bookName)).thenReturn(true);

        assertFalse(library.isAvailable(book1));
        assertThrows(BookNotAvailableException.class, () -> library.checkout(bookName));
    }

    @Test
    void shouldBeAbleToReturnBookByNameSoThatItsAvailable() throws BookNotAvailableException, BookAlreadyReturnedException {
        Book book1 = mock(Book.class);
        Library library = new Library(new ArrayList<>(Arrays.asList(book1)));
        String bookName = "someName";
        when(book1.isName(bookName)).thenReturn(true);

        library.checkout(bookName);
        library.returnBook(bookName);

        assertTrue(library.isAvailable(book1));
    }

    @Test
    void shouldThrowExceptionWhenBookReturnedTwice() throws BookNotAvailableException {
        Book book1 = mock(Book.class);
        Library library = new Library(new ArrayList<>(Arrays.asList(book1)));
        String bookName = "someName";
        when(book1.isName(bookName)).thenReturn(true);

        library.checkout(bookName);
        library.returnBook(bookName);

        assertThrows(BookNotAvailableException.class, () -> library.returnBook(bookName));

    }


}
