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
        String input = Files.readString(Path.of(fileName));
        fileName = fileName.replace(".ks", "");
        /*String filePath = "KSharp";
        String input = "func main(){ func:number(); };" +
                "func number(){ num y = 12; print y; }";*/
        inputReader.handleInput(input, fileName);
    }
}
