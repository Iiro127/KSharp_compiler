package org.example.Variables.Num;

import org.example.Variables.VarResource;
import org.example.asm.NumResource;


public class NumHandler {
    private static final MathHandler mathHandler = new MathHandler();
    private static final VarResource varResource = new VarResource();
    private static NumResource numResource = new NumResource();

    /**
     * Handles num-variables.
     *
     * @param line
     */
    public void handleNum(String line){
        if (line.contains("=")){
            String[] parts = line.split("=");
            String name = parts[0].replace("num", "").trim();

            if (!varResource.isValidName(name)){
                System.err.println("Error at \"" + line + "\": Not a valid name.");
            } else {
                Integer value = Integer.parseInt(parts[1].replace("=", "").trim());
                numResource.addToMap(name, value);
            }
        } else if (line.contains("+")) {
            mathHandler.addition(line);
        } else if (line.contains("-")) {
            mathHandler.subtraction(line);
        }

    }
}
