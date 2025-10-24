package org.example.asm.when;

import org.example.Flow.when.WhenHandler;
import org.example.InputReader;
import org.example.PrintHandler;
import org.example.Variables.Num.NumHandler;
import org.example.Variables.Str.StrHandler;
import org.objectweb.asm.Label;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;
import static org.example.asm.ByteCodeResource.mv;

public class WhenASTResource {
    private static final InputReader inputReader = new InputReader();
    private static final NumHandler numHandler = new NumHandler();
    private static final StrHandler strHandler = new StrHandler();
    private static final PrintHandler printHandler = new PrintHandler();
    private static final WhenHandler whenHandler = new WhenHandler();
    public void emitEquals(String type, String name, Object value, String body) throws IOException {
        Label labelEnd = new Label();

        switch (type) {
            case "num" -> {
                mv.visitFieldInsn(GETSTATIC, "KSharp", "nums", "Ljava/util/Map;");
                mv.visitLdcInsn(name);
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
                mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
                mv.visitLdcInsn(value);
                mv.visitJumpInsn(IF_ICMPNE, labelEnd);
            }
            case "str" -> {
                mv.visitFieldInsn(GETSTATIC, "KSharp", "strs", "Ljava/util/Map;");
                mv.visitLdcInsn(name);
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
                mv.visitTypeInsn(CHECKCAST, "java/lang/String");
                mv.visitLdcInsn(value);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
                mv.visitJumpInsn(IFEQ, labelEnd);
            }
        }

        // Body
        for (String line : inputReader.parseLines(body)) {
            if (line.startsWith("print")) printHandler.handlePrint(line);
            if (line.startsWith("num")) numHandler.handleNum(line);
            if (line.startsWith("str")) strHandler.handleStr(line);
            if (line.startsWith("when")) whenHandler.handleWhen(line);
        }

        mv.visitLabel(labelEnd);
    }

}
