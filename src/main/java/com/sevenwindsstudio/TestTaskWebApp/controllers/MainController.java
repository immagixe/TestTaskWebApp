package com.sevenwindsstudio.TestTaskWebApp.controllers;

import com.sevenwindsstudio.TestTaskWebApp.dto.PersonDTO;
import com.sevenwindsstudio.TestTaskWebApp.model.Person;
import com.sevenwindsstudio.TestTaskWebApp.services.PeopleService;
import com.sevenwindsstudio.TestTaskWebApp.dto.ErrorResponseDto;
import com.sevenwindsstudio.TestTaskWebApp.exceptions.PersonNotCreatedException;
import com.sevenwindsstudio.TestTaskWebApp.exceptions.PersonNotFoundException;

import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/people")
public class MainController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public MainController(PeopleService peopleService,
                          ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get person from database")
    public PersonDTO getPerson(@PathVariable("id") int id) {
        return convertToPersonDTO(peopleService.findOne(id));
    }

    @PostMapping
    @ApiOperation("Save person to database")
    public ResponseEntity<HttpStatus> createPerson(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }

        peopleService.save(convertToPerson(personDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponseDto> handleException(PersonNotFoundException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                "Person with this id wasn't found!", System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponseDto> handleException(PersonNotCreatedException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }
}
