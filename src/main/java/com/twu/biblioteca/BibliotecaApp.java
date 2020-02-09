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
        System.out.println("S.no | Book Name | Author | Year of Publication");
        String book1 = "1 | Old man and the sea | Earnest Hemingway | 2012";
        String book2 = "2 | To Kill A Mocking Bird | Harper Collins | 2013";
        List<String> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        for (String book : bookList) {
            System.out.println(book);
        }
    }

    public static String greeting() {
        return GREETING_MESSAGE;
    }
}
