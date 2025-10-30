package org.example.Func;

import org.example.InputReader;
import org.example.Variables.VarResource;
import org.example.asm.Func.Funcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FuncHandler {
    private static final VarResource varResource = new VarResource();

    public static Map<String, ArrayList<String>> getAllFuncs(String input) throws Exception {
        Map<String, ArrayList<String>> funcs = new HashMap<>();

        Pattern pattern = Pattern.compile("(?s)func\\s+(\\w+)\\s*\\([^)]*\\)\\s*\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String name = matcher.group(1);
            String body = matcher.group(2).trim();

            if (varResource.isValidName(name)) {
                if (name.equals("main")) {
                    if (funcs.containsKey("main"))
                        throw new Exception("Duplicate main function");
                }
                funcs.put(name, InputReader.parseLines(body));
                System.out.println(name);
            } else {
                throw new Exception("Invalid function name: " + name);
            }
        }

        if (!funcs.containsKey("main")){
            throw new Exception("Missing main()");
        } else {
            return funcs;
        }
    }
    public static void handleFuncCall(String input) {
        input = input.replaceAll("func:", "").trim();
        Funcs.handleFuncCall(input);
    }
}
