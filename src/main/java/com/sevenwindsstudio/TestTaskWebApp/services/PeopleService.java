package com.sevenwindsstudio.TestTaskWebApp.services;

import com.sevenwindsstudio.TestTaskWebApp.model.Person;
import com.sevenwindsstudio.TestTaskWebApp.repositories.PeopleRepository;
import com.sevenwindsstudio.TestTaskWebApp.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person findOne (int id) {
        return peopleRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
}
