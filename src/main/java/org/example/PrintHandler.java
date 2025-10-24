package org.example;


import org.example.asm.Prints;

public class PrintHandler {
    private static final Prints prints = new Prints();

    /**
     * Handles all printing.
     *
     * @param line
     */
    public void handlePrint(String line){
        String expr = line.replace("print", "").trim();

        if (expr.contains("//")){
            prints.emitPrint(expr);
        } else {
            prints.emitPrintVariable(expr);
        }
    }
}
