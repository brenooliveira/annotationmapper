package annotationmapper.mapper.support.from;

import annotationmapper.mapper.MapTo;
import annotationmapper.mapper.MapWithReference;
import annotationmapper.mapper.support.adapter.SchoolingAdapterMapper;
import annotationmapper.mapper.support.to.Endereco;

public class Person {

    @MapTo("fullname")
    private String name;

    @MapTo("ages")
    private Integer age;

    @MapTo(value = "street", methodName = "fulladdress")
    private Address street;

    @MapTo(value = "endereco")
    @MapWithReference(Endereco.class)
    private Address address;

    @MapTo(value = "sexo", methodName = "toString")
    private Gender gender;

    @MapTo(value = "escolaridade", adapter = SchoolingAdapterMapper.class)
    private Schooling schooling;

    public Person() {
    }

    public Person(final String name, final Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public Address getStreet() {
        return street;
    }

    public void setStreet(final Address street) {
        this.street = street;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public Schooling getSchooling() {
        return schooling;
    }

    public void setSchooling(final Schooling schooling) {
        this.schooling = schooling;
    }

}
