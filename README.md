# Java Annotation Mapper #

Java annotation mapper is a lib to convert an object into another object using java annotations.

### Exemple: ###

Convert class Person into Human

person.java
~~~java
public class Person {

    @MapTo("fullname")
    private String name;

    @MapTo("ages")
    private Integer age;

    // getters and setters
}
~~~

human.java
~~~java
public class Human {

    public String fullname;
    public Integer ages;
    //getters and setters
}
~~~

AnnotationMapperTest.java
~~~java
public class AnnotationMapperTest {

    @Test
    public void deveReceberUmResourceQualquerERetornarUmOutroResourceQualquer() {
        Person person = new Person("Breno Oliveira", 30);

        Human human = AnnotationMapper.map(person, Human.class);
        assertEquals(person.getName(), human.getFullname());
        assertEquals(person.getAge(), human.getAges());
    }
}
~~~
