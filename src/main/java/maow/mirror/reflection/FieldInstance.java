package maow.mirror.reflection;

import maow.mirror.util.Cache;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;

/**
 * Provides an instance of a field, can be used to get the value of said field.
 */
public class FieldInstance {
    private final String name;
    private final ClassInstance parent;
    private final Field field;

    private FieldInstance(@Nonnull final String name, @Nonnull final ClassInstance parent, @Nonnull final Field field) {
        this.name = name;
        this.parent = parent;
        this.field = field;
    }

    @Nonnull
    public static FieldInstance from(@Nonnull final String name, @Nonnull final ClassInstance parent, @Nonnull final Field field) {
        return new FieldInstance(name, parent, field);
    }

    @Nullable
    public Object getValue() {
        try {
            field.setAccessible(true);
            return field.get(Cache.getObjectFromCache(parent));
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nonnull
    public final Field getField() {
        return field;
    }

    @Nonnull
    public final ClassInstance getParent() {
        return parent;
    }

    @Nonnull
    public final String getName() {
        return name;
    }
}
