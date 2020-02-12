package com.twu.biblioteca;

import com.twu.biblioteca.Exceptions.LibraryItemNotAvailableException;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<LibraryItem> itemList;
    private List<LibraryItem> checkedOutList = new ArrayList<>();

    public Library(List<LibraryItem> itemList) {
        this.itemList = itemList;
    }

    public boolean isAvailable(LibraryItem book) {
        return itemList.contains(book);
    }

    public void checkout(LibraryItem checkoutLibraryItem) throws LibraryItemNotAvailableException {
        if (isAvailable(checkoutLibraryItem))
            itemList.remove(checkoutLibraryItem);
        else
            throw new LibraryItemNotAvailableException();
    }

    public void checkout(String bookName) throws LibraryItemNotAvailableException {
        boolean bookAvailable = false;
        for (LibraryItem libraryItem : itemList) {
            if (libraryItem.isName(bookName)) {
                checkedOutList.add(libraryItem);
                bookAvailable = true;
            }
        }
        if (!bookAvailable)
            throw new LibraryItemNotAvailableException();
        itemList.removeIf(book -> book.isName(bookName));
    }

    public List<String> itemDetails(Signature signature) {
        List<String> bookListString = new ArrayList<>();
        for (LibraryItem libraryItem : itemList) {
            if (libraryItem.signature().equals(signature))
                bookListString.add(libraryItem.columnedProperties());
        }
        return bookListString;
    }

    public void returnLibraryItem(String itemName) throws LibraryItemNotAvailableException {
        boolean bookAvailable = false;
        for (LibraryItem book : checkedOutList) {
            if (book.isName(itemName)) {
                itemList.add(book);
                bookAvailable = true;
            }
        }
        if (!bookAvailable)
            throw new LibraryItemNotAvailableException();
        checkedOutList.removeIf(book -> book.isName(itemName));
    }
}
