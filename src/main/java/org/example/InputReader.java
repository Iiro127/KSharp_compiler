package org.example;

import org.example.Flow.when.WhenHandler;
import org.example.Func.FuncHandler;
import org.example.Variables.Num.NumHandler;
import org.example.Variables.Str.StrHandler;
import org.example.asm.ByteCodeResource;
import org.example.asm.Func.Funcs;

import javax.naming.NamingException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputReader {
    public static Map<String, String> strings = new HashMap<>();
    public static Map<String, Integer> integers = new HashMap<>();
    private static ByteCodeResource byteCodeResource = null;

    /**
     * Handles console input.
     *
     * @param input
     */
    public void handleInput(String input, String filePath) throws Exception {
        byteCodeResource = new ByteCodeResource();
        Funcs.compileFunctions(FuncHandler.getAllFuncs(input));

        byteCodeResource.createClass(Path.of(System.getProperty("user.dir"), filePath.replace(".ks", "") + ".class").toString());
    }

    public static ArrayList<String> parseLines(String input) {
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
     * Reads input
     *
     * @param input
     * @throws IOException
     */
    public static void readInput(String input) throws IOException, NamingException {
        for (String line : parseLines(input)) {
            switch (line) {
                case String s when s.startsWith("num") -> NumHandler.handleNum(s);
                case String s when s.startsWith("str") -> StrHandler.handleStr(s);
                case String s when s.startsWith("print") -> PrintHandler.handlePrint(s);
                case String s when s.startsWith("when") -> WhenHandler.handleWhen(s);
                case String s when s.startsWith("func") -> FuncHandler.handleFunc(s);
                default -> System.out.println("Unknown command: " + line);
            }
        }
    }
}
