package org.example.asm.Variables;

import org.example.App;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.POP;
import static org.example.asm.ByteCodeResource.mv;

public class Strs {
    public void addToMap(String name, String value){
        mv.visitFieldInsn(GETSTATIC, App.fileName, "strs", "Ljava/util/Map;");
        mv.visitLdcInsn(name);
        mv.visitLdcInsn(value);
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        mv.visitInsn(POP);
    }
}
