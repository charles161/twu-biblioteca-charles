package com.twu.biblioteca;

import java.util.List;

public interface Printer extends PrintGreeting, PrintAvailableBooks, PrintMenuItems, PrintErrorMessage, PrintMessage {
}

interface PrintGreeting {
    void printGreeting(String message);
}

interface PrintAvailableBooks {
    void printAvailableBooks(String title, String header, List<String> bookList);
}

interface PrintMenuItems {
    void printMenuItems(String title, List<MenuOptions> menuOptions);
}

interface PrintErrorMessage {
    void printErrorMessage(String message);
}

interface PrintMessage {
    void printMessage(String message);
}
