package com.twu.biblioteca;

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

    public static String buildList(List<Book> bookList) {
        StringBuilder outputString = new StringBuilder();
        int serial = 0;
        for (Book book : bookList) {
            outputString.append(++serial + " | " + columnedProperties(book));
        }
        return outputString.toString();
    }

    private static String columnedProperties(Book book) {
        return book.name + " | " + book.author + " | " + book.yearOfPublication + "\n";
    }
}
