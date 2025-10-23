package org.example.asm.when;

import org.example.Flow.when.WhenHandler;
import org.example.InputReader;
import org.example.PrintHandler;
import org.example.Variables.Num.NumHandler;
import org.example.Variables.Str.StrHandler;
import org.objectweb.asm.Label;

import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static org.example.asm.ByteCodeResource.mv;

public class WhenASTResource {
    private static final InputReader inputReader = new InputReader();
    private static final NumHandler numHandler = new NumHandler();
    private static final StrHandler strHandler = new StrHandler();
    private static final PrintHandler printHandler = new PrintHandler();
    private static final WhenHandler whenHandler = new WhenHandler();
    private static final Label labelEnd = new Label();
    public void emitNumEquals(String name, Integer value, String body) throws IOException {
        mv.visitFieldInsn(GETSTATIC, "KSharp", "nums", "Ljava/util/Map;");
        mv.visitLdcInsn(name);
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
        mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
        mv.visitLdcInsn(value);
        mv.visitJumpInsn(IF_ICMPNE, labelEnd);

        // Cannot directly use inputReader.readInput(body) due to context issues, hence why this is here.
        for (String line : inputReader.parseLines(body)) {
            if (line.startsWith("print")) printHandler.handlePrint(line);
            if (line.startsWith("num")) numHandler.handleNum(line);
            if (line.startsWith("str")) strHandler.handleStr(line);
            if (line.startsWith("when")) whenHandler.handleWhen(line);
        }

        mv.visitLabel(labelEnd);
    }
}
