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

    public void checkout(Book checkoutBook) throws BookNotAvailableException {
        if (isAvailable(checkoutBook))
            bookList.remove(checkoutBook);
        else
            throw new BookNotAvailableException();
    }

    public void checkout(String bookName) throws BookNotAvailableException {
        bookList.removeIf(book -> book.isName(bookName));
    }
}
