package annotationmapper;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

import net.vidageek.mirror.dsl.Mirror;
import annotationmapper.mapper.MapTo;
import annotationmapper.mapper.MapWithReference;
import annotationmapper.mapper.adapter.AdapterMapper;

public class AnnotationMapper {

    public static <T, K> T map(final K resource, final Class<T> clazz) {

        try {

            T destination = instance(clazz, resource);
            for (Field field : resource.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(MapTo.class)) {

                    String fieldName = field.getAnnotation(MapTo.class).value();
                    Object value = getValueFromObj(resource, field);
                    new Mirror().on(destination).set().field(fieldName).withValue(value);
                }
            }

            return destination;

        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T, K> T instance(final Class<T> clazz, final K resource) throws InstantiationException,
            IllegalAccessException {
        if (clazz.isArray()) {
            return (T) Array.newInstance(clazz, Array.getLength(resource));
        } else {
            return clazz.newInstance();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static <K> Object getValueFromObj(final K resource, final Field field) throws InstantiationException,
            IllegalAccessException {

        String methodToInvoke = field.getAnnotation(MapTo.class).methodName();
        Class<? extends AdapterMapper> adapterClass = field.getAnnotation(MapTo.class).adapter();

        // Call adapters
        Object value = new Mirror().on(resource).get().field(field);

        // if (field.getType().isArray()) {
        // System.out.println("==================================================");
        // Class<?> reference =
        // field.getAnnotation(MapWithReference.class).value();
        // return ArrayAnnotationMapper.map(value, reference, field.getType());
        // }

        // Call adpters

        if (hasConcreteAdpter(adapterClass)) {
            AdapterMapper adapter = adapterClass.newInstance();
            if (value != null)
                return adapter.map(value);
        }

        // Call CollectionMapper and call reference
        if (field.isAnnotationPresent(MapWithReference.class)) {
            Object currentResource = value;
            if (currentResource != null) {
                Class<?> reference = field.getAnnotation(MapWithReference.class).value();
                if (isTypeOfCollection(field.getType())) {
                    Class<? extends Collection> collectionType = (Class<? extends Collection>) field.getType();
                    return CollectionAnnotationMapper.map((Collection) currentResource, reference, collectionType);

                }

                return map(currentResource, reference);
            }
        }

        // Call methodInvoke
        if (methodToInvoke.isEmpty()) {
            return value;
        } else {
            Object currentResource = value;
            if (currentResource != null)
                return new Mirror().on(currentResource).invoke().method(methodToInvoke).withoutArgs();
        }

        return null;
    }

    @SuppressWarnings("rawtypes")
    private static boolean hasConcreteAdpter(final Class<? extends AdapterMapper> adapter) {
        return adapter != AdapterMapper.class && !adapter.isInterface()
                && AdapterMapper.class.isAssignableFrom(adapter);
    }

    private static boolean isTypeOfCollection(final Class<?> clazz) {
        for (Class<?> currentClazz : clazz.getInterfaces()) {
            if (Collection.class.isAssignableFrom(currentClazz))
                return true;
        }
        return false;
    }

}