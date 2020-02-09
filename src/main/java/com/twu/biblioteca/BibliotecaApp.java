package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static final String GREETING_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String ERROR_MESSAGE = "Please select a valid option!";
    private static final String BOOK_LIST_HEADER = "S.no | Book Name | Author | Year of Publication";
    private static final String BOOK_LIST_TITLE = "Books Available:";
    private static final String MENU_LIST_TITLE = "Menu: (Type the corresponding number to select)";
    private static final String MENU_OPTION_1 = "1. List of books";
    private Printer printer;

    public BibliotecaApp(Printer printer) {
        this.printer = printer;
    }

    public void displayGreeting() {
        this.printer.printGreeting(GREETING_MESSAGE);
    }

    public void displayMenu() {
        this.printer.printMenuItems(MENU_LIST_TITLE, Collections.singletonList(MENU_OPTION_1));
    }

    public void processUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int menuOption = scanner.nextInt();
            if (menuOption == 1) {
                this.displayBookList();
            } else {
                this.printer.printErrorMessage(ERROR_MESSAGE);
            }
        }
    }

    private void displayBookList() {
        Book book1 = new Book("Old man and the sea", "Earnest Hemingway", 2012);
        Book book2 = new Book("To Kill A Mocking Bird", "Harper Collins", 2013);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        List<String> bookDetails = Book.buildList(bookList);
        this.printer.printAvailableBooks(BOOK_LIST_TITLE, BOOK_LIST_HEADER, bookDetails);
    }

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(new ConsolePrinter());
        bibliotecaApp.displayGreeting();
        bibliotecaApp.displayMenu();
        bibliotecaApp.processUserInput();
    }
}
