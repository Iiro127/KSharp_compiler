package org.example;

import javax.naming.NamingException;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    private static final InputReader inputReader = new InputReader();

    public static void main( String[] args ) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: ksharpc <source.ks>");
            //return;
        }

        //String filePath = args[0];
        //String input = Files.readString(Path.of(filePath));
        String filePath = "KSharp";
        String input = "func main(){ str x = hello; print x//; };" +
                "func number(){ num y = 12; print y//; }";
        inputReader.handleInput(input, filePath);
    }
}
