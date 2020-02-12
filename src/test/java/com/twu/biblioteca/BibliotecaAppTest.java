package com.twu.biblioteca;

import com.ginsberg.junit.exit.ExpectSystemExit;
import com.twu.biblioteca.Exceptions.LibraryItemNotAvailableException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class BibliotecaAppTest {

    private static final String GREETING_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String ERROR_MESSAGE = "Please select a valid option!";
    private static final String ENTER_BOOK_MESSAGE = "Please enter a book name";
    private static final String SUCCESSFUL_BOOK_CHECKOUT_MESSAGE = "Thank you! Enjoy the book";
    private static final String UNSUCCESSFUL_BOOK_CHECKOUT_MESSAGE = "Sorry, that book is not available";
    private static final String SUCCESSFUL_RETURN_MESSAGE = "Thank you for returning the book";
    private static final String UNSUCCESSFUL_RETURN_MESSAGE = "That is not a valid book to return.";
    private static final String ENTER_MOVIE_MESSAGE = "Please enter a movie name";
    private static final String SUCCESSFUL_MOVIE_CHECKOUT_MESSAGE = "Thank you! Enjoy the movie";
    private static final String UNSUCCESSFUL_MOVIE_CHECKOUT_MESSAGE = "Sorry, that movie is not available";

    Printer printer = mock(Printer.class);
    Library library = mock(Library.class);
    InputReceiver inputReceiver = mock(InputReceiver.class);
    BibliotecaApp bibliotecaApp = new BibliotecaApp(printer, library, inputReceiver);

    private void simulateBookNameInput(int userInput, String bookName) {
        when(inputReceiver.hasNextInt()).thenReturn(true, false);
        when(inputReceiver.hasNextLine()).thenReturn(true);
        when(inputReceiver.nextLine()).thenReturn("\n", bookName);
        when(inputReceiver.nextInt()).thenReturn(userInput);
    }


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
    public void shouldBeAbleToViewTheListOfBooksAvailableAfterTypingOneInMenu() {
        int userInput = 1;
        when(inputReceiver.hasNextInt()).thenReturn(true, false);
        when(inputReceiver.nextInt()).thenReturn(userInput);

        bibliotecaApp.processUserInput();

        verify(library, times(1)).itemDetails(Signature.BOOK);
        verify(printer, times(1)).printListItems(Mockito.anyString(), Mockito.anyString(), Mockito.anyList());
    }

    @Test
    public void shouldNotifyUserWhenAnInvalidOption100IsSelected() {
        int userInput = 100;
        when(inputReceiver.hasNextInt()).thenReturn(true, false);
        when(inputReceiver.nextInt()).thenReturn(userInput);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printErrorMessage(ERROR_MESSAGE);
    }


    @Test
    void shouldDisplayTheListOfBooksForAValidInputAfterAnInvalidInput() {
        int invalidInput = 7;
        int validInput = 1;
        when(inputReceiver.hasNextInt()).thenReturn(true, true, false);
        when(inputReceiver.nextInt()).thenReturn(invalidInput, validInput);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printErrorMessage(ERROR_MESSAGE);
        verify(printer, times(1)).printListItems(Mockito.anyString(), Mockito.anyString(), Mockito.anyList());
    }

    @Test
    @ExpectSystemExit
    void shouldExitTheApplicationWhen2IsTyped() {
        int userInput = 2;
        when(inputReceiver.hasNextInt()).thenReturn(true, false);
        when(inputReceiver.nextInt()).thenReturn(userInput);

        bibliotecaApp.processUserInput();

        verify(printer, times(0)).printErrorMessage(ERROR_MESSAGE);
    }

    @Test
    public void shouldDisplayTheEnterBookMessageWhenCheckoutBookOptionIsSelected() {
        int userInput = 3;
        when(inputReceiver.hasNextInt()).thenReturn(true, false);
        when(inputReceiver.nextInt()).thenReturn(userInput);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(ENTER_BOOK_MESSAGE);
    }

    @Test
    public void shouldCheckoutABookWhenNameIsEntered() throws LibraryItemNotAvailableException {
        int userInput1 = 3;
        String bookName = "Old man and the sea";
        int userInput2 = 1;
        when(inputReceiver.hasNextInt()).thenReturn(true, true, false);
        when(inputReceiver.hasNextLine()).thenReturn(true);
        when(inputReceiver.nextLine()).thenReturn("\n", bookName);
        when(inputReceiver.nextInt()).thenReturn(userInput1, userInput2);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(ENTER_BOOK_MESSAGE);
        verify(library, times(1)).checkout(bookName, Signature.BOOK);
    }


    @Test
    void shouldDisplaySuccessMessageAfterSuccessfulCheckout() throws LibraryItemNotAvailableException {
        int userInput1 = 3;
        String bookName = "To Kill A Mocking Bird";
        simulateBookNameInput(userInput1, bookName);
        doNothing().when(library).checkout(bookName, Signature.BOOK);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(SUCCESSFUL_BOOK_CHECKOUT_MESSAGE);
    }

    @Test
    void shouldDisplayErrorMessageAfterUnsuccessfulCheckout() throws LibraryItemNotAvailableException {
        int userInput1 = 3;
        String bookName = "To Kill A Mocking Bird";
        simulateBookNameInput(userInput1, bookName);
        doThrow(LibraryItemNotAvailableException.class).when(library).checkout(bookName, Signature.BOOK);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(UNSUCCESSFUL_BOOK_CHECKOUT_MESSAGE);
    }

    @Test
    void shouldBeAbleToReturnABookWhenABookNameIsEntered() throws LibraryItemNotAvailableException {
        int userInput1 = 4;
        String bookName = "To Kill A Mocking Bird";
        simulateBookNameInput(userInput1, bookName);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(ENTER_BOOK_MESSAGE);
        verify(library, times(1)).returnLibraryItem(Mockito.anyString());
    }

    @Test
    void shouldDisplaySuccessMessageAfterSuccessfulReturn() throws LibraryItemNotAvailableException {
        int userInput1 = 4;
        String bookName = "To Kill A Mocking Bird";
        simulateBookNameInput(userInput1, bookName);
        doNothing().when(library).returnLibraryItem(bookName);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(SUCCESSFUL_RETURN_MESSAGE);
    }

    @Test
    void shouldDisplayErrorMessageAfterUnsuccessfulReturn() throws LibraryItemNotAvailableException {
        int userInput1 = 4;
        String bookName = "To Kill A Mocking Bird";
        simulateBookNameInput(userInput1, bookName);
        doThrow(LibraryItemNotAvailableException.class).when(library).returnLibraryItem(bookName);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(UNSUCCESSFUL_RETURN_MESSAGE);
    }

    @Test
    void shouldDisplayTheListOfMovieDetailsIf5IsSelectedInMenu() {
        int userInput = 5;
        when(inputReceiver.hasNextInt()).thenReturn(true, false);
        when(inputReceiver.nextInt()).thenReturn(userInput);

        bibliotecaApp.processUserInput();

        verify(library, times(1)).itemDetails(Signature.MOVIE);
        verify(printer, times(1)).printListItems(Mockito.anyString(), Mockito.anyString(), Mockito.anyList());
    }

    @Test
    public void shouldDisplayTheEnterMovieMessageWhenCheckoutMovieOptionIsSelected() {
        int userInput = 6;
        when(inputReceiver.hasNextInt()).thenReturn(true, false);
        when(inputReceiver.nextInt()).thenReturn(userInput);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(ENTER_MOVIE_MESSAGE);
    }

    @Test
    public void shouldCheckoutAMovieWhenNameIsEntered() throws LibraryItemNotAvailableException {
        int userInput1 = 6;
        String movieName = "someName";
        int userInput2 = 1;
        when(inputReceiver.hasNextInt()).thenReturn(true, true, false);
        when(inputReceiver.hasNextLine()).thenReturn(true);
        when(inputReceiver.nextLine()).thenReturn("\n", movieName);
        when(inputReceiver.nextInt()).thenReturn(userInput1, userInput2);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(ENTER_MOVIE_MESSAGE);
        verify(library, times(1)).checkout(movieName, Signature.MOVIE);
    }

    @Test
    void shouldDisplaySuccessMessageAfterSuccessfulCheckoutOfMovie() throws LibraryItemNotAvailableException {
        int userInput1 = 6;
        String bookName = "movie";
        simulateBookNameInput(userInput1, bookName);
        doNothing().when(library).checkout(bookName, Signature.MOVIE);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(SUCCESSFUL_MOVIE_CHECKOUT_MESSAGE);
    }

    @Test
    void shouldDisplayErrorMessageAfterUnsuccessfulCheckoutOfMovie() throws LibraryItemNotAvailableException {
        int userInput1 = 6;
        String bookName = "movie";
        simulateBookNameInput(userInput1, bookName);
        doThrow(LibraryItemNotAvailableException.class).when(library).checkout(bookName, Signature.MOVIE);

        bibliotecaApp.processUserInput();

        verify(printer, times(1)).printMessage(UNSUCCESSFUL_MOVIE_CHECKOUT_MESSAGE);
    }
}