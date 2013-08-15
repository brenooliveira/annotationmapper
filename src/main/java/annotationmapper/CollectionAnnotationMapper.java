package annotationmapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CollectionAnnotationMapper {

    @SuppressWarnings({ "rawtypes", "serial" })
    private static final Map<Class<? extends Collection>, Class<? extends Collection>> collectionInstances = new HashMap<Class<? extends Collection>, Class<? extends Collection>>() {
        {
            put(List.class, ArrayList.class);
            put(Set.class, HashSet.class);
            put(ArrayList.class, ArrayList.class);
            put(HashSet.class, HashSet.class);
        }
    };

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T extends Collection> Collection map(final T collection, final Class<?> clazz,
            final Class<? extends Collection> destinationClazzType) throws InstantiationException,
            IllegalAccessException {

        Collection newCollection = findConcretClazz(destinationClazzType).newInstance();
        Iterator i = collection.iterator();
        while (i.hasNext()) {
            newCollection.add(AnnotationMapper.map(i.next(), clazz));
        }
        return newCollection;
    }

    @SuppressWarnings("rawtypes")
    private static Class<? extends Collection> findConcretClazz(final Class<? extends Collection> clazz) {
        return collectionInstances.get(clazz);
    }
}
