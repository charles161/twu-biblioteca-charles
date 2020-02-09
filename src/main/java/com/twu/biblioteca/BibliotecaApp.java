package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static final String GREETING_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public static void main(String[] args) {
        System.out.println(greeting());
        System.out.println();
        viewBookList();
    }

    private static void viewBookList() {
        System.out.println("Books Available:");
        System.out.println();
        String book1 = "Old man and the sea";
        String book2 = "To kill a mocking bird";
        List<String> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        int serial = 0;
        for (String book : bookList) {
            System.out.println(++serial + ". " + book);
        }
    }

    public static String greeting() {
        return GREETING_MESSAGE;
    }
}
