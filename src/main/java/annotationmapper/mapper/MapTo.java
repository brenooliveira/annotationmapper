package annotationmapper.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import annotationmapper.mapper.adapter.AdapterMapper;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapTo {

    public String value();

    public String methodName() default "";

    public Class<? extends AdapterMapper> adapter() default AdapterMapper.class;

}
