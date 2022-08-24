package com.sevenwindsstudio.TestTaskWebApp.repositories;

import com.sevenwindsstudio.TestTaskWebApp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository <Person, Integer> {
}
