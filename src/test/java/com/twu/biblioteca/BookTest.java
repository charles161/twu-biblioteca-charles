package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void shouldReturnThePropertiesOfABookSeparatedByPipes() {
        Book book = new Book("Sherlock Holmes", "Sir Arthur Conan Doyle", 1999);

        String expectedString = "Sherlock Holmes | Sir Arthur Conan Doyle | 1999";
        assertEquals(expectedString, book.columnedProperties());
    }
}