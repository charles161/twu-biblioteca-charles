package com.twu.biblioteca;

import java.util.List;
import java.util.Map;

public interface Printer extends PrintGreeting, PrintListItems, PrintMenuItems, PrintErrorMessage, PrintMessage {
}

interface PrintGreeting {
    void printGreeting(String message);
}

interface PrintListItems {
    void printListItems(String title, String header, List<String> listItems);
}

interface PrintMenuItems {
    void printMenuItems(String title, Map<Integer, MenuOption> menuOptions);
}

interface PrintErrorMessage {
    void printErrorMessage(String message);
}

interface PrintMessage {
    void printMessage(String message);
}
