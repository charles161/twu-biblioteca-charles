package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void shouldReturnThePropertiesOfABookSeparatedByPipes() {
        Book book = new Book("Sherlock Holmes", "Sir Arthur Conan Doyle", 1999);

        String bookDetail = "Sherlock Holmes | Sir Arthur Conan Doyle | 1999";

        assertEquals(bookDetail, book.columnedProperties());
    }

    @Test
    void shouldReturnTrueIfBookNameMatchesWithTheString() {
        Book book = new Book("Sherlock Holmes", "Sir Arthur Conan Doyle", 1999);

        String bookName = "Sherlock Holmes";

        assertTrue(book.isName(bookName));
    }

    @Test
    void shouldReturnFalseIfBookNameDoesNotMatchWithTheString() {
        Book book = new Book("Sherlock Holmes", "Sir Arthur Conan Doyle", 1999);

        String bookName = "Sherlock Conan";

        assertFalse(book.isName(bookName));
    }
}