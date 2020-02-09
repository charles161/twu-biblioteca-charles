package com.twu.biblioteca;

import java.util.List;

public interface Printer {
    void printGreeting(String message);

    void printAvailableBooks(String title, String header, List<String> bookList);

    void printMenuItems(String title, List<String> menuOptions);

    void printErrorMessage(String message);
}
