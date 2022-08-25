package com.sevenwindsstudio.TestTaskWebApp;

import com.sevenwindsstudio.TestTaskWebApp.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@SpringBootTest
public class PersonTests {

    private Person person;

    @BeforeEach
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

        Assertions.assertEquals(0, constraintViolations.size());
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

        Assertions.assertEquals(1, constraintViolations.size());
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

        Assertions.assertEquals(1, constraintViolations.size());
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

        Assertions.assertEquals(1, constraintViolations.size());
    }
}
