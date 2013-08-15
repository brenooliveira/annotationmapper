package annotationmapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayAnnotationMapper {

    @SuppressWarnings("unchecked")
    public static <T> T[] map(final T array, final Class<?> clazz, final Class<?> destinationClazzType)
            throws InstantiationException, IllegalAccessException {

        List<T> newArray = new ArrayList<T>(Arrays.asList(array));
        ArrayList<T> newCollection = (ArrayList<T>) CollectionAnnotationMapper
                .map(newArray, clazz, newArray.getClass());

        return (T[]) newCollection.toArray();
    }
}
