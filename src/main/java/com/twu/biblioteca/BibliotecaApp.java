package com.twu.biblioteca;

import com.twu.biblioteca.Exceptions.LibraryItemNotAvailableException;

import java.util.*;

public class BibliotecaApp {

    private static final String GREETING_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String ERROR_MESSAGE = "Please select a valid option!";
    private static final String ENTER_BOOK_MESSAGE = "Please enter a book name";
    private static final String BOOK_LIST_HEADER = "S.no | Book Name | Author | Year of Publication";
    private static final String BOOK_LIST_TITLE = "Books Available:";
    private static final String MENU_LIST_TITLE = "Menu: (Type the corresponding number to select)";
    private static final String SUCCESSFUL_CHECKOUT_MESSAGE = "Thank you! Enjoy the book";
    private static final String UNSUCCESSFUL_CHECKOUT_MESSAGE = "Sorry, that book is not available";
    private static final String SUCCESSFUL_RETURN_MESSAGE = "Thank you for returning the book";
    private static final String UNSUCCESSFUL_RETURN_MESSAGE = "That is not a valid book to return.";

    private Printer printer;
    private Library library;
    private Input input;
    Map<Integer, MenuOption> menuOptionMap = new HashMap<>();

    public BibliotecaApp(Printer printer, Library library, Input input) {
        this.printer = printer;
        this.library = library;
        this.input = input;
        initialiseMenu();
    }

    private void initialiseMenu() {
        menuOptionMap.put(1, new MenuOption() {
            @Override
            public String title() {
                return "List of books";
            }

            @Override
            public void onSelect() {
                printer.printAvailableBooks(BOOK_LIST_TITLE, BOOK_LIST_HEADER, library.itemDetails());
            }
        });
        menuOptionMap.put(2, new MenuOption() {
            @Override
            public String title() {
                return "Quit";
            }

            @Override
            public void onSelect() {
                System.exit(0);
            }
        });
        menuOptionMap.put(3, new MenuOption() {
            @Override
            public String title() {
                return "Checkout";
            }

            @Override
            public void onSelect() {
                printer.printMessage(ENTER_BOOK_MESSAGE);
                if (input.hasNextLine()) {
                    input.nextLine();
                    String bookName = input.nextLine().replace("\n", "");
                    try {
                        library.checkout(bookName);
                        printer.printMessage(SUCCESSFUL_CHECKOUT_MESSAGE);
                    } catch (LibraryItemNotAvailableException e) {
                        printer.printMessage(UNSUCCESSFUL_CHECKOUT_MESSAGE);
                    }
                }

            }
        });
        menuOptionMap.put(4, new MenuOption() {
            @Override
            public String title() {
                return "Return";
            }

            @Override
            public void onSelect() {
                printer.printMessage(ENTER_BOOK_MESSAGE);
                if (input.hasNextLine()) {
                    input.nextLine();
                    String bookName = input.nextLine().replace("\n", "");
                    try {
                        library.returnLibraryItem(bookName);
                        printer.printMessage(SUCCESSFUL_RETURN_MESSAGE);
                    } catch (LibraryItemNotAvailableException e) {
                        printer.printMessage(UNSUCCESSFUL_RETURN_MESSAGE);
                    }
                }
            }
        });
    }

    public void displayGreeting() {
        this.printer.printGreeting(GREETING_MESSAGE);
    }

    public void displayMenu() {
        this.printer.printMenuItems(MENU_LIST_TITLE, menuOptionMap);
    }

    public void processUserInput() {
        while (input.hasNextInt()) {
            int menuOption = input.nextInt();
            if (menuOptionMap.containsKey(menuOption)) {
                menuOptionMap.get(menuOption).onSelect();
            } else
                this.printer.printErrorMessage(ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws LibraryItemNotAvailableException {
        Book book1 = new Book("Old man and the sea", "Earnest Hemingway", 2012);
        Book book2 = new Book("To Kill A Mocking Bird", "Harper Collins", 2013);
        List<LibraryItem> bookList = new ArrayList<>() {
            {
                add(book1);
                add(book2);
            }
        };
        BibliotecaApp bibliotecaApp = new BibliotecaApp(new ConsolePrinter(), new Library(bookList), new InputWrapper(new Scanner(System.in)));
        bibliotecaApp.displayGreeting();
        bibliotecaApp.displayMenu();
        bibliotecaApp.processUserInput();
    }

}


