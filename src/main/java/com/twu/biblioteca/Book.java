package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private final String name;
    private final String author;
    private final int yearOfPublication;

    public Book(String name, String author, int yearOfPublication) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public static String columnedProperties(Book book) {
        return book.name + " | " + book.author + " | " + book.yearOfPublication;
    }

    public boolean isName(String bookName) {
        return this.name.equals(bookName);
    }
}
