package maow.mirror.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class ReflectionUtil {
    @Nullable
    public static Class<?> getClazz(@Nonnull final String name) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    @Nonnull
    public static Set<Field> getFields(@Nonnull final Class<?> clazz) {
        final Field[] fields = clazz.getDeclaredFields();
        return new HashSet<>(Arrays.asList(fields));
    }

    @Nonnull
    public static Set<Method> getMethods(@Nonnull final Class<?> clazz) {
        final Method[] methods = clazz.getDeclaredMethods();
        return new HashSet<>(Arrays.asList(methods));
    }
}
