package org.example.asm.Variables;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static jdk.internal.org.objectweb.asm.Opcodes.POP;
import static org.example.asm.ByteCodeResource.mv;

public class Nums {
    public void addToMap(String name, Integer value){
        mv.visitFieldInsn(GETSTATIC, "KSharp", "nums", "Ljava/util/Map;");
        mv.visitLdcInsn(name);
        mv.visitLdcInsn(value);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        mv.visitInsn(POP);
    }
    public void addToConditionMap(String name, Integer value){
        //something
    }
}
