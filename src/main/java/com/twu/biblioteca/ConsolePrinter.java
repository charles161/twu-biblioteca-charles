package com.twu.biblioteca;

import java.util.List;
import java.util.Map;

public class ConsolePrinter implements Printer {

    @Override
    public void printGreeting(String message) {
        System.out.println(message);
        System.out.println();
    }

    @Override
    public void printListItems(String title, String header, List<String> listItems) {
        System.out.println(title);
        System.out.println();
        System.out.println(header);
        int serial = 0;
        for (String bookDetails : listItems) {
            System.out.println(++serial + " | " + bookDetails);
        }
        System.out.println();
    }

    @Override
    public void printMenuItems(String title, Map<Integer, MenuOption> menuOptions) {
        System.out.println(title);
        System.out.println();

        for (Map.Entry mapElement : menuOptions.entrySet()) {
            MenuOption option = (MenuOption) mapElement.getValue();
            Integer serial = (Integer) mapElement.getKey();
            System.out.println(serial + ". " + option.title());
        }

        System.out.println();
    }

    @Override
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printMessage(String message) {
        System.out.println();
        System.out.println(message);
    }


}
