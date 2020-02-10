package com.twu.biblioteca;

import com.ginsberg.junit.exit.ExpectSystemExit;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class BibliotecaAppTest {

    private static final String GREETING_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String ERROR_MESSAGE = "Please select a valid option!";
    private static final String ENTER_BOOK_MESSAGE = "Please enter a book name";
    private static final String BOOK_LIST_HEADER = "S.no | Book Name | Author | Year of Publication";
    private static final String BOOK_LIST_TITLE = "Books Available:";
    private static final String MENU_LIST_TITLE = "Menu: (Type the corresponding number to select)";

    Printer printer = mock(Printer.class);
    BibliotecaApp bibliotecaApp = new BibliotecaApp(printer);


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
        verify(printer, times(1)).printMenuItems(Mockito.anyString(), Mockito.anyList());
    }

    @Test
    public void shouldBeAbleToViewTheListOfBooksAvailableAfterTypingOneInMenu() {
        int userInput = 1;
        InputStream inputStream = new ByteArrayInputStream(Integer.toString(userInput).getBytes());
        Book book1 = new Book("Old man and the sea", "Earnest Hemingway", 2012);
        Book book2 = new Book("To Kill A Mocking Bird", "Harper Collins", 2013);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        List<String> bookDetails = Book.buildList(bookList);

        System.setIn(inputStream);
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printAvailableBooks(BOOK_LIST_TITLE, BOOK_LIST_HEADER, bookDetails);
    }

    @Test
    public void shouldNotifyUserWhenAnInvalidOption6IsSelected() {
        int userInput = 6;
        InputStream inputStream = new ByteArrayInputStream(Integer.toString(userInput).getBytes());

        System.setIn(inputStream);
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printErrorMessage(ERROR_MESSAGE);
    }

    @Test
    public void shouldNotifyUserWhenAnInvalidOption4IsSelected() {
        int userInput = 4;
        InputStream inputStream = new ByteArrayInputStream(Integer.toString(userInput).getBytes());

        System.setIn(inputStream);
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printErrorMessage(ERROR_MESSAGE);
    }

    @Test
    void shouldDisplayTheListOfBooksForAValidInputAfterAnInvalidInput() {
        int invalidInput = 7;
        int validInput = 1;
        InputStream inputStream1 = new ByteArrayInputStream((invalidInput + "\n" + validInput).getBytes());
        Book book1 = new Book("Old man and the sea", "Earnest Hemingway", 2012);
        Book book2 = new Book("To Kill A Mocking Bird", "Harper Collins", 2013);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        List<String> bookDetails = Book.buildList(bookList);

        System.setIn(inputStream1);
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printErrorMessage(ERROR_MESSAGE);
        verify(printer, times(1)).printAvailableBooks(BOOK_LIST_TITLE, BOOK_LIST_HEADER, bookDetails);
    }

    @Test
    @ExpectSystemExit
    void shouldExitTheApplicationWhen2IsTyped() {
        int userInput = 2;
        InputStream inputStream1 = new ByteArrayInputStream(Integer.toString(userInput).getBytes());

        System.setIn(inputStream1);
        bibliotecaApp.processUserInput();

        verify(printer, times(0)).printErrorMessage(ERROR_MESSAGE);
    }

    @Test
    public void shouldDisplayTheCheckoutOptionInTheMenu() {
        bibliotecaApp.displayMenu();

        verify(printer, times(1)).printMenuItems(MENU_LIST_TITLE, Arrays.asList(MenuOptions.MENU_OPTION_1, MenuOptions.MENU_OPTION_2, MenuOptions.MENU_OPTION_3));
    }

    @Test
    public void shouldDisplayTheEnterBookMessageWhenCheckoutOptionIsSelected() {
        int userInput = 3;
        InputStream inputStream = new ByteArrayInputStream(Integer.toString(userInput).getBytes());

        System.setIn(inputStream);
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(ENTER_BOOK_MESSAGE);
    }

    @Test
    public void shouldNotDisplayTheNameOfTheBook1WhenCheckedOut() {
        int userInput1 = 3;
        String bookName = "Old man and the sea";
        int userInput2 = 1;
        InputStream inputStream = new ByteArrayInputStream((userInput1 + "\n" + bookName + "\n" + userInput2).getBytes());
        Book book = new Book("To Kill A Mocking Bird", "Harper Collins", 2013);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        List<String> bookDetails = Book.buildList(bookList);

        System.setIn(inputStream);
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(ENTER_BOOK_MESSAGE);
        verify(printer, times(1)).printAvailableBooks(BOOK_LIST_TITLE, BOOK_LIST_HEADER, bookDetails);
    }

    @Test
    public void shouldNotDisplayTheNameOfTheBook2WhenCheckedOut() {
        int userInput1 = 3;
        String bookName = "To Kill A Mocking Bird";
        int userInput2 = 1;
        InputStream inputStream = new ByteArrayInputStream((userInput1 + "\n" + bookName + "\n" + userInput2).getBytes());
        Book book = new Book("Old man and the sea", "Earnest Hemingway", 2012);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        List<String> bookDetails = Book.buildList(bookList);

        System.setIn(inputStream);
        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(ENTER_BOOK_MESSAGE);
        verify(printer, times(1)).printAvailableBooks(BOOK_LIST_TITLE, BOOK_LIST_HEADER, bookDetails);
    }
}