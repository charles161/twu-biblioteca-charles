package com.twu.biblioteca;

public class Book implements LibraryItem {

    private final String name;
    private final String author;
    private final int yearOfPublication;

    public Book(String name, String author, int yearOfPublication) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public String columnedProperties() {
        return name + " | " + author + " | " + yearOfPublication;
    }

    public boolean isName(String bookName) {
        return this.name.equals(bookName);
    }
}
