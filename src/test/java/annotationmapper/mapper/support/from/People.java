package annotationmapper.mapper.support.from;

import java.util.ArrayList;
import java.util.List;

import annotationmapper.mapper.MapTo;
import annotationmapper.mapper.MapWithReference;
import annotationmapper.mapper.support.to.Human;

public class People {

    @MapTo("humans")
    @MapWithReference(Human.class)
    private List<Person> people = new ArrayList<Person>();

    public void add(final Person person) {
        people.add(person);
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(final List<Person> people) {
        this.people = people;
    }
}
