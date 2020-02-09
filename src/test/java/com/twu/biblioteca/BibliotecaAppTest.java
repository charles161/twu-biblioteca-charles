package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BibliotecaAppTest {

    String expectedGreeting = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    String expectedMenu = "Menu: (Type the corresponding number to select)\n\n1. List of books\n";
    String expectedBookList = "Books Available:\n\n" +
            "S.no | Book Name | Author | Year of Publication\n" +
            "1 | Old man and the sea | Earnest Hemingway | 2012\n" +
            "2 | To Kill A Mocking Bird | Harper Collins | 2013\n";
    String expectedExceptionMessage = "Please select a valid option!\n";

    @Test
    public void shouldDisplayGreetingMessageWhenTheApplicationStarts() {
        assertEquals(expectedGreeting, BibliotecaApp.greeting());
    }

    @Test
    public void shouldDisplayAuthorAndYearOfPublicationInTheBookList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        BibliotecaApp.viewBookList();

        assertEquals(expectedBookList, outContent.toString());
    }

    @Test
    public void shouldDisplayTheMenuAfterTheGreeting() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        BibliotecaApp.menu();

        assertEquals(expectedMenu, outContent.toString());
    }

    @Test
    public void shouldBeAbleToViewTheListOfBooksAvailableAfterTypingOneInMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int userInput = 1;
        InputStream in = new ByteArrayInputStream(Integer.toString(userInput).getBytes());
        String expectedOutput = expectedGreeting + "\n\n" + expectedMenu + "\n" + expectedBookList;

        System.setIn(in);
        BibliotecaApp.main(new String[]{});

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void shouldNotifyUserWhenAnInvalidOption2IsSelected() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int userInput = 2;
        InputStream in = new ByteArrayInputStream(Integer.toString(userInput).getBytes());
        String expectedOutput = expectedGreeting + "\n\n" + expectedMenu + "\n" + expectedExceptionMessage;

        System.setIn(in);
        BibliotecaApp.main(new String[]{});

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void shouldNotifyUserWhenAnInvalidOption3IsSelected() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int userInput = 3;
        InputStream in = new ByteArrayInputStream(Integer.toString(userInput).getBytes());
        String expectedOutput = expectedGreeting + "\n\n" + expectedMenu + "\n" + expectedExceptionMessage;

        System.setIn(in);
        BibliotecaApp.main(new String[]{});

        assertEquals(expectedOutput, outContent.toString());
    }
}