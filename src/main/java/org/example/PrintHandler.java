package org.example;


import org.example.asm.PrintResource;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static org.example.ByteCodeResource.mv;

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
