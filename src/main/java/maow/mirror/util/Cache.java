package maow.mirror.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import maow.mirror.reflection.ClassInstance;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Mirror's caches, should not be touched by the end-user unless necessary.
 */
public final class Cache {
    private static final LoadingCache<String, Class<?>> CLASS_CACHE = CacheBuilder.newBuilder()
            .weakKeys()
            .build(new CacheLoader<String, Class<?>>() {
                @Override
                public Class<?> load(@Nonnull final String clazz) {
                    return ReflectionUtil.getClazz(clazz);
                }
            });

    private static final LoadingCache<Class<?>, Set<Field>> FIELDS_CACHE = CacheBuilder.newBuilder()
            .weakKeys()
            .build(new CacheLoader<Class<?>, Set<Field>>() {
                @Override
                public Set<Field> load(@Nonnull final Class<?> clazz) {
                    return ReflectionUtil.getFields(clazz);
                }
            });

    private static final LoadingCache<Class<?>, Set<Method>> METHODS_CACHE = CacheBuilder.newBuilder()
            .weakKeys()
            .build(new CacheLoader<Class<?>, Set<Method>>() {
                @Override
                public Set<Method> load(@Nonnull final Class<?> clazz) {
                    return ReflectionUtil.getMethods(clazz);
                }
            });

    private static final LoadingCache<ClassInstance, Object> OBJECTS_CACHE = CacheBuilder.newBuilder()
            .weakKeys()
            .build(new CacheLoader<ClassInstance, Object>() {
                @Override
                public Object load(@Nonnull final ClassInstance clazzInst) {
                    return clazzInst.create();
                }
            });

    public static Class<?> getClassFromCache(@Nonnull final String name) {
        return CLASS_CACHE.getUnchecked(name);
    }

    public static Set<Field> getFieldsFromCache(@Nonnull final Class<?> clazz) {
        return FIELDS_CACHE.getUnchecked(clazz);
    }

    public static Set<Method> getMethodsFromCache(@Nonnull final Class<?> clazz) {
        return METHODS_CACHE.getUnchecked(clazz);
    }

    public static Object getObjectFromCache(@Nonnull final ClassInstance query) {
        return OBJECTS_CACHE.getUnchecked(query);
    }
}
