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

    public static List<String> buildList(List<Book> bookList) {
        List<String> bookListString = new ArrayList<>();
        for (Book book : bookList) {
            bookListString.add(columnedProperties(book));
        }
        return bookListString;
    }

    private static String columnedProperties(Book book) {
        return book.name + " | " + book.author + " | " + book.yearOfPublication;
    }
}
