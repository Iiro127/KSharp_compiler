package org.example.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class ByteCodeResource {
    public static ClassWriter cw = null;
    public static MethodVisitor mv = null;

    public ByteCodeResource() {
        cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        cw.visit(V17, ACC_PUBLIC, "KSharp", null, "java/lang/Object", null);

        cw.visitField(ACC_PUBLIC + ACC_STATIC, "integers", "Ljava/util/Map;", null, null).visitEnd();
        MethodVisitor clinit = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
        clinit.visitCode();

        clinit.visitTypeInsn(NEW, "java/util/HashMap");
        clinit.visitInsn(DUP);
        clinit.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false);
        clinit.visitFieldInsn(PUTSTATIC, "KSharp", "integers", "Ljava/util/Map;");

        clinit.visitInsn(RETURN);
        clinit.visitMaxs(0, 0);
        clinit.visitEnd();

        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V",  null, null);
        mv.visitCode();

        emitGetVariableMethod();
    }
    public void createClass(String outputName) {
        Path filePath = Path.of(outputName);

        mv.visitInsn(RETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        try {
            Files.deleteIfExists(filePath);
            Files.write(filePath, bytes);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

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
        mv.visitInsn(ACONST_NULL);

        mv.visitLabel(L_end);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
    }

}
