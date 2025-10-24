package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Hello world!
 *
 */
public class App
{
    private static final InputReader inputReader = new InputReader();

    public static void main( String[] args ) throws IOException, URISyntaxException {
        if (args.length == 0) {
            System.out.println("Usage: ksharpc <source.ks>");
            return;
        }

        String filePath = args[0];
        String input = Files.readString(Path.of(filePath));
        inputReader.readInput(input);
    }
}
