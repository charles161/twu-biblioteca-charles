package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsolePrinterTest {

    private static final String GREETING_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String ERROR_MESSAGE = "Please select a valid option!";
    private static final String BOOK_LIST_HEADER = "S.no | Book Name | Author | Year of Publication";
    private static final String BOOK_LIST_TITLE = "Books Available:";
    private static final String MENU_LIST_TITLE = "Menu: (Type the corresponding number to select)";
    private static final String MENU_OPTION_1 = "1. List of books";

    ConsolePrinter consolePrinter = new ConsolePrinter();
    ByteArrayOutputStream outContent;

    @Test
    void shouldBeAbleToPrintGreetingInConsole() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = GREETING_MESSAGE + "\n\n";

        consolePrinter.printGreeting(GREETING_MESSAGE);

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void shouldBeAbleToPrintAvailableBooksInConsole() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String bookListString = "Book ABC";
        String expectedOutput = BOOK_LIST_TITLE + "\n\n" + BOOK_LIST_HEADER + "\n1 | " + bookListString + "\n\n";

        consolePrinter.printAvailableBooks(BOOK_LIST_TITLE, BOOK_LIST_HEADER, Collections.singletonList(bookListString));

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void shouldBeAbleToPrintMenuItemsInConsole() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = MENU_LIST_TITLE + "\n\n" + MENU_OPTION_1 + "\n\n";

        consolePrinter.printMenuItems(MENU_LIST_TITLE, Collections.singletonList(MENU_OPTION_1));

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void shouldBeAbleToPrintErrorMessageInConsole() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = ERROR_MESSAGE + "\n";

        consolePrinter.printErrorMessage(ERROR_MESSAGE);

        assertEquals(expectedOutput, outContent.toString());
    }
}