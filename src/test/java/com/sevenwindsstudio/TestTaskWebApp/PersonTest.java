package com.sevenwindsstudio.TestTaskWebApp;

import com.sevenwindsstudio.TestTaskWebApp.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.*;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

    private Person person;

    @Before
    public void createNewPerson() {
        person = new Person();
    }

    @Test
    public void correctPersonFieldsShouldPassValidate() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        person.setFirstname("Harry");
        person.setSurname("Potter");
        person.setPatronymic("James");
        person.setEmail("h.potter@gryffindor.hogwarts");
        person.setPhoneNumber("1-267-436-5109");

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        Assert.assertEquals(0, constraintViolations.size());
    }

    @Test
    public void firstNameWithOneCharacterLengthShouldNotPassValidate() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        person.setFirstname("H");
        person.setSurname("Potter");
        person.setPatronymic("James");
        person.setEmail("h.potter@gryffindor.hogwarts");
        person.setPhoneNumber("1-267-436-5109");

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        Assert.assertEquals(1, constraintViolations.size());
    }

    @Test
    public void nullSurnameShouldNotPassValidate() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        person.setFirstname("Harry");
        person.setPatronymic("James");
        person.setEmail("h.potter@gryffindor.hogwarts");
        person.setPhoneNumber("1-267-436-5109");

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        Assert.assertEquals(1, constraintViolations.size());
    }

    @Test
    public void incorrectEmailShouldNotPassValidate() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        person.setFirstname("Harry");
        person.setSurname("Potter");
        person.setPatronymic("James");
        person.setEmail("h.potter_gryffindor.hogwarts");
        person.setPhoneNumber("1-267-436-5109");

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        Assert.assertEquals(1, constraintViolations.size());
    }
}
