package org.example.Variables.Num;

import static org.example.InputReader.integers;

public class MathHandler {

    /**
     * Handles addition between two num-variables.
     *
     * @param line
     */
    public Integer addition(String line){
        String[] parts = line.split("\\+");
        String num1 = parts[0].replace("num", "").trim();
        String num2 = parts[1].trim();

        if (integers.containsKey(num1) && integers.containsKey(num2)){
            return integers.get(num1) + integers.get(num2);
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Handles subtraction between two num-variables.
     *
     * @param line
     */
    public Integer subtraction(String line) {
        String[] parts = line.split("\\-");
        String num1 = parts[0].replace("num", "").trim();
        String num2 = parts[1].trim();

        return integers.get(num1) - integers.get(num2);
    }
}
