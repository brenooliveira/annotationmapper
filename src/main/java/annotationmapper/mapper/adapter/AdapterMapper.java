package annotationmapper.mapper.adapter;

public interface AdapterMapper<T, K> {

    K map(T resource);

}