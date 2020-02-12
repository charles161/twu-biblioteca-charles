package com.twu.biblioteca;

import java.util.Scanner;

public class ConsoleInputReceiverReceiver implements InputReceiver {
    Scanner scanner;

    public ConsoleInputReceiverReceiver(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public boolean hasNextInt() {
        return scanner.hasNextInt();
    }

    @Override
    public int nextInt() {
        return scanner.nextInt();
    }
}
