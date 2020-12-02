package maow.test;

import maow.mirror.Mirror;
import maow.mirror.reflection.ClassInstance;
import maow.mirror.reflection.FieldInstance;
import maow.mirror.reflection.MethodInstance;

public class TestMain {
    public static void main(String[] args) {
        ClassInstance clazz = Mirror.getClassInst("maow.test.TestClass", "Cool arg.");
        System.out.println(clazz.create());
        FieldInstance field = Mirror.getFieldInst(clazz, "testField");
        System.out.println(field.getValue());
        MethodInstance method = Mirror.getMethodInst(clazz, "print");
        method.invoke();
    }
}
