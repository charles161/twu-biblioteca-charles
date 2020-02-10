package com.twu.biblioteca;

import com.twu.biblioteca.Exceptions.BookNotAvailableException;

import java.util.List;

public class Library {
    private List<Book> bookList;

    public Library(List<Book> bookList) {
        this.bookList = bookList;
    }

    public boolean isAvailable(Book book) {
        return bookList.contains(book);
    }

    public void checkout(Book book) throws BookNotAvailableException {
        if (isAvailable(book))
            bookList.remove(book);
        else
            throw new BookNotAvailableException();
    }
}
