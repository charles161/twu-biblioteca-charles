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

    public void checkout(String libraryItemName,Signature signature) throws LibraryItemNotAvailableException {
        boolean itemCheckedOut = false;
        Iterator iterator = itemList.iterator();
        while (iterator.hasNext())
        {
            LibraryItem libraryItem = (LibraryItem)iterator.next();
            if (libraryItem.isName(libraryItemName) && libraryItem.signature().equals(signature))
            {
                itemCheckedOut = true;
                checkedOutList.add(libraryItem);
                iterator.remove();
                break;
            }
        }
        if(!itemCheckedOut)
        throw new LibraryItemNotAvailableException();
    }

    public List<String> itemDetails(Signature signature) {
        List<String> libraryItemListString = new ArrayList<>();
        for (LibraryItem libraryItem : itemList) {
            if (libraryItem.signature().equals(signature))
                libraryItemListString.add(libraryItem.columnedProperties());
        }
        return libraryItemListString;
    }

    public void returnLibraryItem(String itemName) throws LibraryItemNotAvailableException {
        boolean libraryItemAvailable = false;
        for (LibraryItem libraryItem : checkedOutList) {
            if (libraryItem.isName(itemName)) {
                itemList.add(libraryItem);
                libraryItemAvailable = true;
            }
        }
        if (!libraryItemAvailable)
            throw new LibraryItemNotAvailableException();
        checkedOutList.removeIf(libraryItem -> libraryItem.isName(itemName));
    }
}
