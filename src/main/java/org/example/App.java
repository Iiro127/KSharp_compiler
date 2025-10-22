package org.example;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    private static final InputReader inputReader = new InputReader();

    public static void main( String[] args ) throws IOException {
        inputReader.readInput("num x = 3; print z; print woah!//;");
    }
}
