package org.example.asm.Variables;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.POP;
import static org.example.asm.ByteCodeResource.mv;

public class Strs {
    public void addToMap(String name, String value){
        mv.visitFieldInsn(GETSTATIC, "KSharp", "strs", "Ljava/util/Map;");
        mv.visitLdcInsn(name);
        mv.visitLdcInsn(value);
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        mv.visitInsn(POP);
    }
}
