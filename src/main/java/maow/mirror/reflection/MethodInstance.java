package maow.mirror.reflection;

import maow.mirror.util.Cache;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;

/**
 * Provides an instance of a method, can be used to invoke said method.
 */
public class MethodInstance {
    private final String name;
    private final ClassInstance parent;
    private final Method method;

    private MethodInstance(@Nonnull final String name, @Nonnull final ClassInstance parent, @Nonnull final Method method) {
        this.name = name;
        this.parent = parent;
        this.method = method;
    }

    @Nonnull
    public static MethodInstance from(@Nonnull final String name, @Nonnull final ClassInstance parent, @Nonnull final Method method) {
         return new MethodInstance(name, parent, method);
    }

    @Nullable
    public Object invoke(@Nonnull final Object... args) {
        try {
            method.setAccessible(true);
            return method.invoke(Cache.getObjectFromCache(parent), args);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nonnull
    public final String getName() {
        return name;
    }

    @Nonnull
    public final ClassInstance getParent() {
        return parent;
    }

    @Nonnull
    public final Method getMethod() {
        return method;
    }
}
