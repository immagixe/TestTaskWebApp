package com.sevenwindsstudio.TestTaskWebApp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class PersonDTO {

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String firstname;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String surname;

    private String patronymic;

    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
}
