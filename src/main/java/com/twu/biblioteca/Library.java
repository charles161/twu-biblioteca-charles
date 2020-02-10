package com.twu.biblioteca;

import java.util.List;

public class Library {
    private List<Book> bookList;

    public Library(List<Book> bookList) {
        this.bookList = bookList;
    }

    public boolean isAvailable(Book book) {
        return bookList.contains(book);
    }

    public void checkout(Book book) {
        bookList.remove(book);
    }
}
