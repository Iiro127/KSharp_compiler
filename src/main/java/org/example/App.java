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
        inputReader.readInput("num x = 2; when (x = 2) { print woah// };");
    }
}
