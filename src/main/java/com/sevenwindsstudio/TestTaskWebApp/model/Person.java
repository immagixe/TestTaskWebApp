package com.sevenwindsstudio.TestTaskWebApp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "people")
@Getter @Setter @NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstname")
    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String firstname;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email")
    @Email (message = "Incorrect Email")
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Person(String firstname, String surname, String patronymic, String email, String phoneNumber) {
        this.firstname = firstname;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
