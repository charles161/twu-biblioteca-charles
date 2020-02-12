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
            if (libraryItem.signature().equals(signature))
                libraryItemListString.add(libraryItem.columnedProperties());
        }
        return libraryItemListString;
    }

    public void checkout(String libraryItemName, Signature signature) throws LibraryItemNotAvailableException {
        replaceLibraryItem(libraryItemName, signature, itemList, checkedOutList);
    }

    public void returnLibraryItem(String libraryItemName) throws LibraryItemNotAvailableException {
        replaceLibraryItem(libraryItemName, Signature.BOOK, checkedOutList, itemList);
    }

    private void replaceLibraryItem(String libraryItemName, Signature signature, List<LibraryItem> inputList, List<LibraryItem> outputList) throws LibraryItemNotAvailableException {
        boolean itemCheckedOut = false;
        Iterator iterator = inputList.iterator();
        while (iterator.hasNext()) {
            LibraryItem libraryItem = (LibraryItem) iterator.next();
            if (libraryItem.isName(libraryItemName) && libraryItem.signature().equals(signature)) {
                itemCheckedOut = true;
                outputList.add(libraryItem);
                iterator.remove();
                break;
            }
        }
        if (!itemCheckedOut)
            throw new LibraryItemNotAvailableException();
    }
}
