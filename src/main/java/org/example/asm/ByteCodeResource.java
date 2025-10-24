package org.example.asm;

import org.example.asm.Variables.VariableResource;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.objectweb.asm.Opcodes.*;

public class ByteCodeResource {
    public static ClassWriter cw = null;
    public static MethodVisitor mv = null;
    private static final VariableResource variableResource = new VariableResource();
    public ByteCodeResource() {
        cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        cw.visit(V17, ACC_PUBLIC, "KSharp", null, "java/lang/Object", null);

        // Maps for variables
        cw.visitField(ACC_PUBLIC + ACC_STATIC, "nums", "Ljava/util/Map;", null, null).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_STATIC, "strs", "Ljava/util/Map;", null, null).visitEnd();

        MethodVisitor clinit = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
        clinit.visitCode();

        // nums = new HashMap<>();
        clinit.visitTypeInsn(NEW, "java/util/HashMap");
        clinit.visitInsn(DUP);
        clinit.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false);
        clinit.visitFieldInsn(PUTSTATIC, "KSharp", "nums", "Ljava/util/Map;");

        // strs = new HashMap<>();
        clinit.visitTypeInsn(NEW, "java/util/HashMap");
        clinit.visitInsn(DUP);
        clinit.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false);
        clinit.visitFieldInsn(PUTSTATIC, "KSharp", "strs", "Ljava/util/Map;");
        clinit.visitInsn(RETURN);
        clinit.visitMaxs(0, 0);
        clinit.visitEnd();

        variableResource.emitGetVariableMethod();

        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V",  null, null);
        mv.visitCode();
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

}
