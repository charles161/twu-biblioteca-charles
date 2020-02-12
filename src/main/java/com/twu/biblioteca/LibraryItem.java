package com.twu.biblioteca;

public interface LibraryItem {
    String columnedProperties();

    boolean isName(String name);

    boolean isSignature(Signature signature);
}
