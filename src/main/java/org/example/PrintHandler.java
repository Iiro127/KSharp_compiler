package org.example;


import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static org.example.ByteCodeResource.mv;

public class PrintHandler {

    /**
     * Handles all printing.
     *
     * @param line
     */
    public void handlePrint(String line){
        String expr = line.replace("print", "").trim();

        if (expr.contains("//")){
            emitPrint(expr);
        } else {
            emitPrintVariable(expr);
        }
    }

    public void emitPrint(String line) {
        String expr = line.replace("print", "").replace("//", "").trim();

        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn(expr);
        mv.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    /**
     * Gets a referenced variable.
     *
     * @param expr
     */
    public void emitPrintVariable(String expr) {
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

        mv.visitLdcInsn(expr.replace("print", "").replace("//", "").trim());
        mv.visitMethodInsn(INVOKESTATIC, "KSharp", "getVariable", "(Ljava/lang/String;)Ljava/lang/String;", false);

        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }
}
