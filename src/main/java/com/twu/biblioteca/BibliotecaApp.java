package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static final String GREETING_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String ERROR_MESSAGE = "Please select a valid option!";
    private static final String ENTER_BOOK_MESSAGE = "Please enter a book name";
    private static final String BOOK_LIST_HEADER = "S.no | Book Name | Author | Year of Publication";
    private static final String BOOK_LIST_TITLE = "Books Available:";
    private static final String MENU_LIST_TITLE = "Menu: (Type the corresponding number to select)";
    Book book1 = new Book("Old man and the sea", "Earnest Hemingway", 2012);
    Book book2 = new Book("To Kill A Mocking Bird", "Harper Collins", 2013);
    List<Book> bookList = new ArrayList<>() {
        {
            add(book1);
            add(book2);
        }
    };

    private Printer printer;

    public BibliotecaApp(Printer printer) {
        this.printer = printer;
    }

    public void displayGreeting() {
        this.printer.printGreeting(GREETING_MESSAGE);
    }

    public void displayMenu() {
        this.printer.printMenuItems(MENU_LIST_TITLE, Arrays.asList(MenuOptions.MENU_OPTION_1, MenuOptions.MENU_OPTION_2, MenuOptions.MENU_OPTION_3));
    }

    public void processUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            switch (scanner.nextInt()) {
                case 1:
                    this.displayBookList();
                    break;
                case 2:
                    System.exit(0);
                case 3:
                    this.printer.printMessage(ENTER_BOOK_MESSAGE);
                    if(scanner.hasNextLine()){
                        scanner.nextLine();
                        if (scanner.hasNext()) {
                            String bookName = scanner.nextLine();
                            bookList.removeIf(book -> book.isName(bookName));
                        }
                    }
                    break;
                default:
                    this.printer.printErrorMessage(ERROR_MESSAGE);
            }
        }
    }

    private void displayBookList() {
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
