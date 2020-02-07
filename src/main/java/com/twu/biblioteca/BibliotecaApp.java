package com.twu.biblioteca;

public class BibliotecaApp {

    public static final String GREETING_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public static void main(String[] args) {
        System.out.println(greeting());
    }

    public static String greeting() {
        return GREETING_MESSAGE;
    }
}
