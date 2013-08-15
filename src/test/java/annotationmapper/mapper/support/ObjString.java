package annotationmapper.mapper.support;

import annotationmapper.mapper.MapTo;

public class ObjString {

    @MapTo("amount")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

}
