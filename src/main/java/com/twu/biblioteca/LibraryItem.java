package com.twu.biblioteca;

public abstract class LibraryItem {
    Signature signature;

    public LibraryItem(Signature signature) {
        this.signature = signature;
    }

    abstract String columnedProperties();

    abstract boolean isName(String name);

    boolean isSignature(Signature signature) {
        return this.signature.equals(signature);
    }

}
