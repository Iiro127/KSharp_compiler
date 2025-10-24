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
        String input = "str x = hello; num y = 3; when (x = hello) { print x equals hello// };" +
                        "when (y = 3) { print y equals 3// };";

        inputReader.readInput(input);
    }
}
