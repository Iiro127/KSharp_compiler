package org.example.Variables;

import java.util.Set;

public class VarResource {
    private static final Set<String> reservedWords = Set.of(
        "str", "num", "print", "when", "func"
    );


    /**
     * Checks if a name is valid. Current criteria:
     *      - No spaces
     *      - No special characters
     *      - No reserved words
     *      - Is not empty
     *
     * @param name
     * @return
     */
    public boolean isValidName(String name){
        return !name.contains(" ") && name.matches("[a-zA-Z0-9]*") && !reservedWords.contains(name) && !name.isEmpty();
    }
}
