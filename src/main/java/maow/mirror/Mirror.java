package maow.mirror;

import maow.mirror.reflection.ClassInstance;
import maow.mirror.reflection.FieldInstance;
import maow.mirror.reflection.MethodInstance;
import maow.mirror.util.Cache;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

/**
 * Main class, provides methods for getting the instances of classes, methods, and fields.
 */
public final class Mirror {
    private Mirror() {}

    @Nonnull
    public static ClassInstance getClassInst(@Nonnull final String name, @Nonnull final Object... args) {
        return ClassInstance.from(name, args, Cache.getClassFromCache(name));
    }

    @Nullable
    public static MethodInstance getMethodInst(@Nonnull final ClassInstance clazz, @Nonnull final String name) {
        final Set<Method> methods = Cache.getMethodsFromCache(clazz.getClazz());
        Optional<Method> optional = methods.stream().filter(method -> method.getName().equals(name)).findFirst();
        return optional.map(method -> MethodInstance.from(name, clazz, method)).orElse(null);
    }

    @Nullable
    public static FieldInstance getFieldInst(@Nonnull final ClassInstance clazz, @Nonnull final String name) {
        final Set<Field> fields = Cache.getFieldsFromCache(clazz.getClazz());
        Optional<Field> optional = fields.stream().filter(field -> field.getName().equals(name)).findFirst();
        return optional.map(field -> FieldInstance.from(name, clazz, field)).orElse(null);
    }
}
