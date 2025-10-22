package org.example.Flow.when;

import org.example.InputReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.example.InputReader.integers;
import static org.example.InputReader.strings;
public class WhenHandler {
    public static final Map<String, String> conditionStr = new HashMap<>();
    public static final Map<String, Integer> conditionInt = new HashMap<>();
    private static final InputReader inputReader = new InputReader();
    private static final WhenResource whenResource = new WhenResource();
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
            String var = parts[0].trim();

            try {
                Integer value = Integer.parseInt(parts[1].replace("=", "").trim());
                conditionInt.put(var, value);
            } catch (Exception e){
                String value = parts[1].replace("=", "").trim();
                conditionStr.put(var, value);
            }

            if (integers.containsKey(var)){
                int stored = integers.get(var);
                int expected = conditionInt.get(var);

                if (stored == expected){
                    inputReader.readInput(whenResource.parseBody(line));
                }
            } else if (strings.containsKey(var)) {
                String stored = strings.get(var);
                String expected = conditionStr.get(var);

                if (stored.equals(expected)){
                    inputReader.readInput(whenResource.parseBody(line));
                }
            }
        }
    }
}
