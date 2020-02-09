package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BibliotecaAppTest {

    @Test
    public void shouldDisplayGreetingMessageWhenTheApplicationStarts() {
        String expectedGreeting = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

        assertEquals(expectedGreeting, BibliotecaApp.greeting());
    }

    @Test
    public void shouldDisplayAuthorAndYearOfPublicationInTheBookList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedGreeting = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        String expectedBookList = "Books Available:\n\n" +
                "S.no | Book Name | Author | Year of Publication\n" +
                "1 | Old man and the sea | Earnest Hemingway | 2012\n" +
                "2 | To Kill A Mocking Bird | Harper Collins | 2013\n";
        String expectedOutput = expectedGreeting + "\n\n" + expectedBookList;

        BibliotecaApp.main(new String[]{});

        assertEquals(expectedOutput, outContent.toString());
    }

}