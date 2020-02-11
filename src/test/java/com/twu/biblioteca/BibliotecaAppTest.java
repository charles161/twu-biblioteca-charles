package com.twu.biblioteca;

import com.ginsberg.junit.exit.ExpectSystemExit;
import com.twu.biblioteca.Exceptions.BookNotAvailableException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class BibliotecaAppTest {

    private static final String GREETING_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String ERROR_MESSAGE = "Please select a valid option!";
    private static final String ENTER_BOOK_MESSAGE = "Please enter a book name";


    Printer printer = mock(Printer.class);
    Library library = mock(Library.class);
    BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, library, new Scanner(System.in));


    @Test
    void shouldDisplayGreetingMessageWhenTheApplicationStarts() {
        bibliotecaApp.displayGreeting();

        verify(printer, times(1)).printGreeting(GREETING_MESSAGE);
    }

    @Test
    public void shouldDisplayTheMenuAfterTheGreeting() {
        bibliotecaApp.displayGreeting();
        bibliotecaApp.displayMenu();

        verify(printer, times(1)).printGreeting(GREETING_MESSAGE);
        verify(printer, times(1)).printMenuItems(Mockito.anyString(), Mockito.anyMap());
    }

    @Test
    public void shouldBeAbleToViewTheListOfBooksAvailableAfterTypingOneInMenu() throws BookNotAvailableException {
        int userInput = 1;
        InputStream inputStream = new ByteArrayInputStream(Integer.toString(userInput).getBytes());

        System.setIn(inputStream);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, library, new Scanner(System.in));
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printAvailableBooks(Mockito.anyString(), Mockito.anyString(), Mockito.anyList());
    }

    @Test
    public void shouldNotifyUserWhenAnInvalidOption6IsSelected() throws BookNotAvailableException {
        int userInput = 6;
        InputStream inputStream = new ByteArrayInputStream(Integer.toString(userInput).getBytes());

        System.setIn(inputStream);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, library, new Scanner(System.in));
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printErrorMessage(ERROR_MESSAGE);
    }

    @Test
    public void shouldNotifyUserWhenAnInvalidOption4IsSelected() throws BookNotAvailableException {
        int userInput = 4;
        InputStream inputStream = new ByteArrayInputStream(Integer.toString(userInput).getBytes());

        System.setIn(inputStream);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, library, new Scanner(System.in));
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printErrorMessage(ERROR_MESSAGE);
    }

    @Test
    void shouldDisplayTheListOfBooksForAValidInputAfterAnInvalidInput() throws BookNotAvailableException {
        int invalidInput = 7;
        int validInput = 1;
        InputStream inputStream1 = new ByteArrayInputStream((invalidInput + "\n" + validInput).getBytes());

        System.setIn(inputStream1);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, library, new Scanner(System.in));
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printErrorMessage(ERROR_MESSAGE);
        verify(printer, times(1)).printAvailableBooks(Mockito.anyString(), Mockito.anyString(), Mockito.anyList());
    }

    @Test
    @ExpectSystemExit
    void shouldExitTheApplicationWhen2IsTyped() throws BookNotAvailableException {
        int userInput = 2;
        InputStream inputStream1 = new ByteArrayInputStream(Integer.toString(userInput).getBytes());

        System.setIn(inputStream1);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, library, new Scanner(System.in));
        bibliotecaApp.processUserInput();

        verify(printer, times(0)).printErrorMessage(ERROR_MESSAGE);
    }

    @Test
    public void shouldDisplayTheEnterBookMessageWhenCheckoutOptionIsSelected() throws BookNotAvailableException {
        int userInput = 3;
        InputStream inputStream = new ByteArrayInputStream(Integer.toString(userInput).getBytes());

        System.setIn(inputStream);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, library, new Scanner(System.in));
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(ENTER_BOOK_MESSAGE);
    }

    @Test
    public void shouldNotDisplayTheNameOfTheBook1WhenCheckedOut() throws BookNotAvailableException {
        int userInput1 = 3;
        String bookName = "Old man and the sea";
        int userInput2 = 1;
        InputStream inputStream = new ByteArrayInputStream((userInput1 + "\n" + bookName + "\n" + userInput2).getBytes());
        Book book = new Book("To Kill A Mocking Bird", "Harper Collins", 2013);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        List<String> bookDetails = Book.buildList(bookList);
        when(library.availableBooksDetail()).thenReturn(bookDetails);

        System.setIn(inputStream);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, library, new Scanner(System.in));
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(ENTER_BOOK_MESSAGE);
        verify(library, times(1)).checkout(bookName);
    }

    @Test
    public void shouldNotDisplayTheNameOfTheBook2WhenCheckedOut() throws BookNotAvailableException {
        int userInput1 = 3;
        String bookName = "To Kill A Mocking Bird";
        int userInput2 = 1;
        InputStream inputStream = new ByteArrayInputStream((userInput1 + "\n" + bookName + "\n" + userInput2).getBytes());
        Book book = new Book("Old man and the sea", "Earnest Hemingway", 2012);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        List<String> bookDetails = Book.buildList(bookList);
        when(library.availableBooksDetail()).thenReturn(bookDetails);

        System.setIn(inputStream);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, library, new Scanner(System.in));
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(ENTER_BOOK_MESSAGE);
        verify(library, times(1)).checkout(bookName);
    }
}