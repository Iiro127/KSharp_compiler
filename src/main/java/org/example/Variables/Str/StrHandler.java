package org.example.Variables.Str;

import org.example.Variables.VarResource;
import org.example.asm.StrResource;

import static org.example.InputReader.strings;

public class StrHandler {
    private static final VarResource varResource = new VarResource();
    private static final StrResource strResource = new StrResource();

    /**
     * Handles str-variables
     *
     * @param line
     */
    public void handleStr(String line){
        if (line.contains("=")){
            String[] parts = line.split("=");
            String name = parts[0].replace("str", "").trim();
            String value = parts[1].replace("=", "").trim();

            if (!varResource.isValidName(name)){
                //return "Error at \"" + line + "\": Not a valid name.";
            } else {
                strResource.addToMap(name, value);
            }
        } else if (line.contains("+")){
            String[] parts = line.split("\\+");
            String str1 = parts[0].replace("str", "").trim();
            String str2 = parts[1].trim();

            if (strings.containsKey(str1) && strings.containsKey(str2)){
                //return strings.get(str1) + strings.get(str2);
            }
        } else {
            //return "Unknown command: " + line;
        }
        //return "";
    }
}
