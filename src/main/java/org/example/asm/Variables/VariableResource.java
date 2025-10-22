package org.example.asm.Variables;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static org.example.asm.ByteCodeResource.cw;

public class VariableResource {
    public void emitGetVariableMethod() {
        MethodVisitor mv = cw.visitMethod(
                ACC_PUBLIC + ACC_STATIC,
                "getVariable",
                "(Ljava/lang/String;)Ljava/lang/String;",
                null,
                null
        );

        mv.visitCode();

        Label L_notFound = new Label();
        Label L_end = new Label();

        mv.visitFieldInsn(GETSTATIC, "KSharp", "integers", "Ljava/util/Map;");
        mv.visitVarInsn(ALOAD, 0); // load expr
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "containsKey", "(Ljava/lang/Object;)Z", true);
        mv.visitJumpInsn(IFEQ, L_notFound);

        mv.visitFieldInsn(GETSTATIC, "KSharp", "integers", "Ljava/util/Map;");
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
        mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "toString", "()Ljava/lang/String;", false);
        mv.visitJumpInsn(GOTO, L_end);

        mv.visitLabel(L_notFound);
        mv.visitTypeInsn(NEW, "java/lang/NullPointerException");
        mv.visitInsn(DUP);
        mv.visitLdcInsn("Variable not found");
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/NullPointerException", "<init>", "(Ljava/lang/String;)V", false);
        mv.visitInsn(ATHROW);

        mv.visitLabel(L_end);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
    }
}
