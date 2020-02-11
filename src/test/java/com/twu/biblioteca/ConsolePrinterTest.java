package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConsolePrinterTest {

    private static final String GREETING_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String ERROR_MESSAGE = "Please select a valid option!";
    private static final String BOOK_LIST_HEADER = "S.no | Book Name | Author | Year of Publication";
    private static final String BOOK_LIST_TITLE = "Books Available:";
    private static final String MENU_LIST_TITLE = "Menu: (Type the corresponding number to select)";

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
        MenuOption menuOption = mock(MenuOption.class);
        Map<Integer, MenuOption> menuOptionMap = new HashMap<>();
        menuOptionMap.put(1, menuOption);

        consolePrinter.printMenuItems(MENU_LIST_TITLE, menuOptionMap);

        verify(menuOption, times(1)).title();
    }

    @Test
    void shouldBeAbleToPrintErrorMessageInConsole() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = ERROR_MESSAGE + "\n";

        consolePrinter.printErrorMessage(ERROR_MESSAGE);

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void shouldBeAbleToPrintMessageInConsole() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = "\ncool\n";

        consolePrinter.printMessage("cool");

        assertEquals(expectedOutput, outContent.toString());
    }
}