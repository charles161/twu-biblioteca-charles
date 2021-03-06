package com.twu.biblioteca;

import com.twu.biblioteca.Exceptions.LibraryItemNotAvailableException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library {
    private List<LibraryItem> itemList;
    private List<LibraryItem> checkedOutList = new ArrayList<>();

    public Library(List<LibraryItem> itemList) {
        this.itemList = itemList;
    }

    public boolean isAvailable(LibraryItem libraryItem) {
        return itemList.contains(libraryItem);
    }

    public List<String> itemDetails(Signature signature) {
        List<String> libraryItemListString = new ArrayList<>();
        for (LibraryItem libraryItem : itemList) {
            if (isEquals(signature, libraryItem))
                libraryItemListString.add(libraryItem.columnedProperties());
        }
        return libraryItemListString;
    }

    public void checkoutBook(String bookName) throws LibraryItemNotAvailableException {
        checkout(bookName, Signature.BOOK);
    }

    public void checkoutMovie(String movieName) throws LibraryItemNotAvailableException {
        checkout(movieName, Signature.MOVIE);
    }

    public void returnBook(String libraryItemName) throws LibraryItemNotAvailableException {
        moveLibraryItem(libraryItemName, Signature.BOOK, checkedOutList, itemList);
    }

    private void moveLibraryItem(String libraryItemName, Signature signature, List<LibraryItem> inputList, List<LibraryItem> outputList) throws LibraryItemNotAvailableException {
        boolean itemCheckedOut = false;
        Iterator iterator = inputList.iterator();
        while (iterator.hasNext()) {
            LibraryItem libraryItem = (LibraryItem) iterator.next();
            if (libraryItem.isName(libraryItemName) && isEquals(signature, libraryItem)) {
                itemCheckedOut = true;
                outputList.add(libraryItem);
                iterator.remove();
                break;
            }
        }
        if (!itemCheckedOut)
            throw new LibraryItemNotAvailableException();
    }

    private boolean isEquals(Signature signature, LibraryItem libraryItem) {
        return libraryItem.isSignature(signature);
    }

    private void checkout(String libraryItemName, Signature signature) throws LibraryItemNotAvailableException {
        moveLibraryItem(libraryItemName, signature, itemList, checkedOutList);
    }
}
