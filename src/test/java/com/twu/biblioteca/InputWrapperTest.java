package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InputWrapperTest {

    @Test
    void shouldReturnTrueWhenNextLineIsPresent() {
        String userInput = "cool";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        InputWrapper inputWrapper = new InputWrapper(new Scanner(System.in));

        assertTrue(inputWrapper.hasNextLine());
    }

    @Test
    void shouldReturnFalseWhenNextLineIsNotPresent() {
        String userInput = "";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        InputWrapper inputWrapper = new InputWrapper(new Scanner(System.in));

        assertFalse(inputWrapper.hasNextLine());
    }

    @Test
    void shouldReturnTheNextLineStringCool() {
        String userInput = "cool";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        InputWrapper inputWrapper = new InputWrapper(new Scanner(System.in));

        assertEquals(userInput, inputWrapper.nextLine());
    }

    @Test
    void shouldReturnTheNextLineStringWarm() {
        String userInput = "warm";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        InputWrapper inputWrapper = new InputWrapper(new Scanner(System.in));

        assertEquals(userInput, inputWrapper.nextLine());
    }

    @Test
    void shouldReturnTrueWhenNextIntIsAvailable() {
        String userInput = "1";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        InputWrapper inputWrapper = new InputWrapper(new Scanner(System.in));

        assertTrue(inputWrapper.hasNextInt());
    }

    @Test
    void shouldReturnFalseWhenNextIntIsNotAvailable() {
        String userInput = "1as";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        InputWrapper inputWrapper = new InputWrapper(new Scanner(System.in));

        assertFalse(inputWrapper.hasNextInt());
    }

    @Test
    void shouldReturnTheNextInt() {
        String userInput = "1";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        InputWrapper inputWrapper = new InputWrapper(new Scanner(System.in));

        int expectedInt = 1;
        assertEquals(expectedInt, inputWrapper.nextInt());
    }
}