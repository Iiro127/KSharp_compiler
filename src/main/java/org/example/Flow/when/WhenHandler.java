package org.example.Flow.when;

import org.example.InputReader;
import org.example.asm.Variables.Nums;
import org.example.asm.when.WhenASTResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WhenHandler {
    public static final Map<String, String> conditionStr = new HashMap<>();
    //public static final Map<String, Integer> conditionInt = new HashMap<>();
    private static final InputReader inputReader = new InputReader();
    private static final WhenASTResource whenASTResource = new WhenASTResource();
    private static final WhenResource whenResource = new WhenResource();
    private static final Nums nums = new Nums();
    private static String line;

    /**
     * Handles when-statement.
     *
     * @param input
     */
    public void handleWhen(String input) throws IOException {
        line = input;
        String condition = whenResource.parseCondition(line);
        handleCondition(condition);
    }

    /**
     * Handles when-condition.
     *
     * @param condition
     */
    private void handleCondition(String condition) throws IOException {
        if (condition.contains("=")){

            String[] parts = condition.split("=");
            String name = parts[0].trim();

            // This to AST?
            try {
                Integer value = Integer.parseInt(parts[1].replace("=", "").trim());
                nums.addToConditionMap(name, value);
                whenASTResource.checkIfNumEquals(name, value);
            } catch (Exception e){
                String value = parts[1].replace("=", "").trim();
                conditionStr.put(name, value);
            }

        }
    }
}
