package org.example;

import javax.naming.NamingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Hello world!
 *
 */
public class App
{
    private static final InputReader inputReader = new InputReader();
    public static String fileName = "";
    public static void main( String[] args ) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: ksharpc <source.ks>");
            return;
        }

        fileName = args[0];
        if (fileName.equals("--version")){
            System.out.println("KSharp compiler 1.0.1");
        } else {
            String input = Files.readString(Path.of(fileName));
            fileName = fileName.replace(".ks", "");
            inputReader.handleInput(input, fileName);
        }
    }
}
