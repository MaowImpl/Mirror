package maow.mirror.reflection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * Provides an instance of a class, can be used to get fields and methods.
 */
public class ClassInstance {
    private final String name;
    private final Object[] args;
    private final Class<?> clazz;

    private ClassInstance(@Nonnull final String name, @Nonnull final Object[] args, @Nonnull final Class<?> clazz) {
        this.name = name;
        this.args = args;
        this.clazz = clazz;
    }

    @Nonnull
    public static ClassInstance from(@Nonnull final String name, @Nonnull final Object[] args, @Nonnull final Class<?> clazz) {
        return new ClassInstance(name, args, clazz);
    }

    @Nullable
    public Object create() {
        try {
            final Class<?>[] classes = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
            final Constructor<?> constructor = clazz.getDeclaredConstructor(classes);
            constructor.setAccessible(true);
            return constructor.newInstance(args);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nonnull
    public final Class<?> getClazz() {
        return clazz;
    }

    @Nonnull
    public final Object[] getArgs() {
        return args;
    }

    @Nonnull
    public final String getName() {
        return name;
    }
}
