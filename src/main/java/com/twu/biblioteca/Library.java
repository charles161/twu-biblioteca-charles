package com.twu.biblioteca;

import com.twu.biblioteca.Exceptions.BookAlreadyReturnedException;
import com.twu.biblioteca.Exceptions.BookNotAvailableException;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> bookList;
    private List<Book> checkedOutList = new ArrayList<>();

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
        boolean bookAvailable = false;
        for (Book book : bookList) {
            if (book.isName(bookName)) {
                checkedOutList.add(book);
                bookAvailable = true;
            }
        }
        if (!bookAvailable)
            throw new BookNotAvailableException();
        bookList.removeIf(book -> book.isName(bookName));
    }

    public List<String> availableBooksDetail() {
        List<String> bookListString = new ArrayList<>();
        for (Book book : bookList) {
            bookListString.add(Book.columnedProperties(book));
        }
        return bookListString;
    }

    public void returnBook(String bookName) throws BookNotAvailableException {
        boolean bookAvailable = false;
        for (Book book : checkedOutList) {
            if (book.isName(bookName)) {
                bookList.add(book);
                bookAvailable = true;
            }
        }
        if (!bookAvailable)
            throw new BookNotAvailableException();
        checkedOutList.removeIf(book -> book.isName(bookName));
    }
}
