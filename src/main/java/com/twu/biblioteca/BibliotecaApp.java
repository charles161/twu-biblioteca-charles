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
    private static final String MOVIE_LIST_HEADER = "S.no | Name | Year | Director | Rating";
    private static final String MOVIE_LIST_TITLE = "Movies Available:";

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
                printer.printListItems(BOOK_LIST_TITLE, BOOK_LIST_HEADER, library.itemDetails(Signature.BOOK));
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
        menuOptionMap.put(5, new MenuOption() {
            @Override
            public String title() {
                return "List of movies";
            }

            @Override
            public void onSelect() {
                printer.printListItems(MOVIE_LIST_TITLE, MOVIE_LIST_HEADER, library.itemDetails(Signature.MOVIE));
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

    public static void main(String[] args) {
        Book book1 = new Book("Old man and the sea", "Earnest Hemingway", 2012);
        Book book2 = new Book("To Kill A Mocking Bird", "Harper Collins", 2013);
        Movie movie1 = new Movie("3 idiots", 2009, "Rajkumar", 6);
        Movie movie2 = new Movie("The Chronicles of Narnia", 2005, "Andrew Adamson", 7);
        List<LibraryItem> itemList = new ArrayList<>(Arrays.asList(book1,book2,movie1,movie2));
        BibliotecaApp bibliotecaApp = new BibliotecaApp(new ConsolePrinter(), new Library(itemList), new InputWrapper(new Scanner(System.in)));
        bibliotecaApp.displayGreeting();
        bibliotecaApp.displayMenu();
        bibliotecaApp.processUserInput();
    }

}


