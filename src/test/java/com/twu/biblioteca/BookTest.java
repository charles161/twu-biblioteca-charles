package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void shouldReturnThePropertiesOfABookSeparatedByPipesWithSerialNumber() {
        Book book = new Book("Sherlock Holmes", "Sir Arthur Conan Doyle", 1999);

        String expectedString = "1 | Sherlock Holmes | Sir Arthur Conan Doyle | 1999\n";
        assertEquals(expectedString, Book.buildList(Collections.singletonList(book)));
    }
}