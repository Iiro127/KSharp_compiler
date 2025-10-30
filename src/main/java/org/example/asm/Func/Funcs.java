package org.example.asm.Func;

import org.example.App;
import org.example.InputReader;
import org.example.asm.ByteCodeResource;
import org.objectweb.asm.MethodVisitor;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.example.asm.ByteCodeResource.cw;
import static org.objectweb.asm.Opcodes.*;

public class Funcs {

    public static void compileFunctions(Map<String, ArrayList<String>> funcs) throws IOException, NamingException {
        for (Map.Entry<String, ArrayList<String>> entry : funcs.entrySet()) {
            String name = entry.getKey();
            ArrayList<String> body = entry.getValue();

            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, name, "()V", null, null);
            MethodVisitor oldMv = ByteCodeResource.mv;
            ByteCodeResource.mv = mv;
            mv.visitCode();

            try {
                for (String line : body) {
                    InputReader.readInput(line);
                }
            } finally {
                ByteCodeResource.mv = oldMv;
            }

            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();

            ByteCodeResource.mv = oldMv;
        }
    }
    public static void handleFuncCall(String input) {
        String funcName = input.replace("()", "").trim();

        ByteCodeResource.mv.visitMethodInsn(INVOKESTATIC, App.fileName, funcName, "()V", false);
    }
}
