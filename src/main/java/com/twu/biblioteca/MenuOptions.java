package com.twu.biblioteca;

public enum MenuOptions {
    MENU_OPTION_1("1. List of books"),
    MENU_OPTION_2("2. Quit"),
    MENU_OPTION_3("3. Checkout");

    private String optionName;

    MenuOptions(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionName() {
        return optionName;
    }
}
