package annotationmapper.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import annotationmapper.AnnotationMapper;
import annotationmapper.mapper.support.ObjInteger;
import annotationmapper.mapper.support.ObjString;
import annotationmapper.mapper.support.from.Address;
import annotationmapper.mapper.support.from.Gender;
import annotationmapper.mapper.support.from.People;
import annotationmapper.mapper.support.from.Person;
import annotationmapper.mapper.support.from.Schooling;
import annotationmapper.mapper.support.to.Community;
import annotationmapper.mapper.support.to.Endereco;
import annotationmapper.mapper.support.to.Escolaridade;
import annotationmapper.mapper.support.to.Human;

public class AnnotationMapperTest {

    @Test
    public void deveReceberUmResourceQualquerERetornarUmOutroResourceQualquer() {
        Person person = new Person("Breno Oliveira", 30);

        Human human = AnnotationMapper.map(person, Human.class);
        assertEquals(person.getName(), human.getFullname());
        assertEquals(person.getAge(), human.getAges());
    }

    @Test
    public void quandoInformadoMethodNameEleDeveSerChamadoAoInvesDoGetter() {
        Person person = new Person("Breno Oliveira", 30);
        Address address = new Address("Rua dos Karatecas", "Faixa Preta");
        person.setStreet(address);

        Human human = AnnotationMapper.map(person, Human.class);
        assertEquals(person.getStreet().fulladdress(), human.getStreet());
    }

    @Test
    public void naoInvocarMetodoInformadoQuandoObjetoForNulo() {
        Person person = new Person();
        Human human = AnnotationMapper.map(person, Human.class);

        assertNull(human.getStreet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void quandoValoresParaTiposDiferentesDeveLancarExcecao() {

        ObjString objStr = new ObjString();
        objStr.setValue("blá");

        AnnotationMapper.map(objStr, ObjInteger.class);
    }

    @Test
    public void quandoUmReferenceForInformadoDeveMapearUmResource() {
        Person person = new Person("Breno Oliveira", 30);
        Address address = new Address("Rua dos Karatecas", "Faixa Preta");
        person.setAddress(address);

        Human human = AnnotationMapper.map(person, Human.class);
        Endereco endereco = human.getEndereco();
        assertEquals(address.getStreet(), endereco.getRua());
        assertEquals(address.getNumber(), endereco.getNumero());

    }

    @Test
    public void quandoInformadoUmaColecaoDeveMapearTodosElementosInternos() {
        Person person1 = buildPerson();
        People people = new People();
        people.add(person1);
        Person person2 = buildPerson();
        people.add(person2);

        Community community = AnnotationMapper.map(people, Community.class);

        assertNotNull(community.getHumans());
        Human human1 = buildHuman(person1);
        Human human2 = buildHuman(person2);

        assertTrue(community.getHumans().contains(human1));
        assertTrue(community.getHumans().contains(human2));
    }

    @Test
    public void deveLancaoExceptionEmReferenciasCirculares() {
        // Assert.fail();
    }

    @Test
    public void deveMapearUmaStringParaUmEnum() {
        Person person = new Person();
        person.setGender(Gender.M);

        Human human = AnnotationMapper.map(person, Human.class);

        assertEquals(human.getSexo(), "M");
    }

    @Test
    public void deveUtilizarDoMeuAdapterPersonalizado() {
        Person person = new Person();
        person.setSchooling(Schooling.GRADUATE);

        Human human = AnnotationMapper.map(person, Human.class);

        assertEquals(human.getEscolaridade(), Escolaridade.GRADUADO);
    }

    // @Test
    // public void deveMapearDeUmArrayParaOutro() {
    // Person person1 = new Person("Jamal Maleak", 19);
    // Person person2 = new Person("Latika Maleak", 21);
    // Person[] people = new Person[] { person1, person2 };
    //
    // Human[] humans = AnnotationMapper.map(people, Human[].class);
    //
    // assertEquals(person1.getName(), humans[0].getFullname());
    // assertEquals(person1.getAge(), humans[0].getAges());
    //
    // assertEquals(person2.getName(), humans[1].getFullname());
    // assertEquals(person2.getAge(), humans[1].getAges());
    // }

    private Person buildPerson() {
        return new Person("Jamal Maleak" + new Date(), 29);
    }

    private Human buildHuman(final Person person) {
        return new Human(person.getName(), person.getAge());
    }

}