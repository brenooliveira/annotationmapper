package annotationmapper.mapper.support.from;

import annotationmapper.mapper.MapTo;

public class Address {

    @MapTo("rua")
    private String street;
    @MapTo("numero")
    private String number;

    public Address() {
    }

    public Address(final String street, final String number) {
        this.street = street;
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public String fulladdress() {
        return String.format("%s, %s", street, number);
    }
}
