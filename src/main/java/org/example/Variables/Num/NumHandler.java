package org.example.Variables.Num;

import org.example.Variables.VarResource;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static org.example.ByteCodeResource.mv;


public class NumHandler {
    private static final MathHandler mathHandler = new MathHandler();
    private static final VarResource varResource = new VarResource();

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
            } else {
                Integer value = Integer.parseInt(parts[1].replace("=", "").trim());
                addToMap(name, value);
            }
        } else if (line.contains("+")) {
            mathHandler.addition(line);
        } else if (line.contains("-")) {
            mathHandler.subtraction(line);
        }

    }

    private void addToMap(String name, Integer value){
        mv.visitFieldInsn(GETSTATIC, "KSharp", "integers", "Ljava/util/Map;");
        mv.visitLdcInsn(name);
        mv.visitLdcInsn(value); // load primitive
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        mv.visitInsn(POP);
    }
}
