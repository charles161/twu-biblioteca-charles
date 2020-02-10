package com.twu.biblioteca;

import com.twu.biblioteca.Exceptions.BookNotAvailableException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {
    @Test
    void shouldReturnTrueIfBooksAreAvailable() {
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);
        List<Book> bookList = new ArrayList<>() {
            {
                add(book1);
                add(book2);
            }
        };
        Library library = new Library(bookList);


        assertTrue(library.isAvailable(book2));
        assertTrue(library.isAvailable(book1));
    }

    @Test
    void shouldReturnFalseIfBooksAreNotAvailable() {
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);
        List<Book> bookList = new ArrayList<>();
        Library library = new Library(bookList);

        assertFalse(library.isAvailable(book2));
        assertFalse(library.isAvailable(book1));
    }

    @Test
    void shouldBeAbleToCheckoutABookIfItIsAvailable() throws BookNotAvailableException {
        Book book1 = mock(Book.class);
        List<Book> bookList = new ArrayList<>() {
            {
                add(book1);
            }
        };
        Library library = new Library(bookList);

        assertTrue(library.isAvailable(book1));
        library.checkout(book1);
        assertFalse(library.isAvailable(book1));
    }

    @Test
    void shouldThrowExceptionWhenCheckingOutABookThatIsUnavailable() {
        Book book1 = mock(Book.class);
        List<Book> bookList = new ArrayList<>();
        Library library = new Library(bookList);

        assertFalse(library.isAvailable(book1));
        assertThrows(BookNotAvailableException.class, () -> library.checkout(book1));
    }

    @Test
    void shouldBeAbleToCheckoutBookByName() throws BookNotAvailableException {
        Book book1 = mock(Book.class);
        List<Book> bookList = new ArrayList<>() {{
            add(book1);
        }};
        Library library = new Library(bookList);
        String bookName = "someName";
        when(book1.isName(bookName)).thenReturn(true);

        library.checkout(bookName);

        assertFalse(library.isAvailable(book1));
    }
}
