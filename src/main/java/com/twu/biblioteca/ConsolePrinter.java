package com.twu.biblioteca;

import java.util.List;

public class ConsolePrinter implements Printer {

    @Override
    public void printGreeting(String message) {
        System.out.println(message);
        System.out.println();
    }

    @Override
    public void printAvailableBooks(String title, String header, List<String> bookList) {
        System.out.println(title);
        System.out.println();
        System.out.println(header);
        int serial = 0;
        for (String bookDetails : bookList) {
            System.out.println(++serial + " | " + bookDetails);
        }
        System.out.println();
    }

    @Override
    public void printMenuItems(String title, List<String> menuOptions) {
        System.out.println(title);
        System.out.println();
        for (String menuOption : menuOptions) {
            System.out.println(menuOption);
        }
        System.out.println();
    }

    @Override
    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
