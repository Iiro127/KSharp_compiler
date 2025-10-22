package org.example.asm.when;

import org.objectweb.asm.Label;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static org.example.asm.ByteCodeResource.mv;

public class WhenASTResource {
    private static Label labelEnd = new Label();
    public void checkIfNumEquals(String name, Integer value){
        mv.visitFieldInsn(GETSTATIC, "KSharp", "integers", "Ljava/util/Map;");
        mv.visitLdcInsn("x");
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
        mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
        mv.visitLdcInsn(5);
        mv.visitJumpInsn(IF_ICMPNE, labelEnd);

        //TODO: Figure out how to read the input.
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("ok");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        mv.visitLabel(labelEnd);
    }
}
