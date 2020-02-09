package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    public static final String GREETING_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public static void main(String[] args) {
        System.out.println(greeting());
        System.out.println();
        menu();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int menuOption = scanner.nextInt();
        if (menuOption == 1) {
            viewBookList();
        } else {
            System.out.println("Please select a valid option!");
        }
    }

    public static void menu() {
        System.out.println("Menu: (Type the corresponding number to select)");
        System.out.println();
        System.out.println("1. List of books");
    }

    public static void viewBookList() {
        System.out.println("Books Available:");
        System.out.println();
        System.out.println("S.no | Book Name | Author | Year of Publication");
        Book book1 = new Book("Old man and the sea", "Earnest Hemingway", 2012);
        Book book2 = new Book("To Kill A Mocking Bird", "Harper Collins", 2013);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        System.out.print(Book.buildList(bookList));
    }

    public static String greeting() {
        return GREETING_MESSAGE;
    }
}
