package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaAppTest {

    @Test
    public void shouldDisplayGreetingMessageWhenTheApplicationStarts() {
        String expectedGreeting = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        assertEquals(expectedGreeting, BibliotecaApp.greeting());
    }

}