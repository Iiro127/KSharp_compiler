package org.example;

import org.example.Flow.when.WhenHandler;
import org.example.Variables.Num.NumHandler;
import org.example.Variables.Str.StrHandler;
import org.example.asm.ByteCodeResource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputReader {
    public static Map<String, String> strings = new HashMap<>();
    public static Map<String, Integer> integers = new HashMap<>();
    private static final NumHandler numHandler = new NumHandler();
    private static final StrHandler strHandler = new StrHandler();
    private static final PrintHandler printHandler = new PrintHandler();
    private static final WhenHandler whenHandler = new WhenHandler();
    private static final ByteCodeResource byteCodeResource = new ByteCodeResource();

    public ArrayList<String> parseLines(String input) {
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder instruction = new StringBuilder();

        for (char letter : input.toCharArray()) {
            if (letter == ';') {
                lines.add(instruction.toString().trim());
                instruction.setLength(0);
            } else {
                instruction.append(letter);
            }
        }

        if (!instruction.isEmpty()) {
            lines.add(instruction.toString().trim());
        }

        return lines;
    }

    /**
     * Reads console input.
     *
     * @param input
     */
    public void readInput(String input) throws IOException {
        for (String line : parseLines(input)) {
            switch (line) {
                case String s when s.startsWith("num") -> numHandler.handleNum(s);
                case String s when s.startsWith("str") -> strHandler.handleStr(s);
                case String s when s.startsWith("print") -> printHandler.handlePrint(s);
                case String s when s.startsWith("when") -> whenHandler.handleWhen(s);
                default -> System.out.println("Unknown command: " + line);
            }
        }

        byteCodeResource.createClass(Path.of(System.getProperty("user.dir"), "KSharp.class").toString());
    }
}
