package org.example;


import org.example.asm.PrintResource;

public class PrintHandler {
    private static final PrintResource printResource = new PrintResource();

    /**
     * Handles all printing.
     *
     * @param line
     */
    public void handlePrint(String line){
        String expr = line.replace("print", "").trim();

        if (expr.contains("//")){
            printResource.emitPrint(expr);
        } else {
            printResource.emitPrintVariable(expr);
        }
    }
}
