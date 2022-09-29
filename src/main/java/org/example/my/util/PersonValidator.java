package org.example.my.util;

import org.example.my.models.Person;
import org.example.my.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonValidator(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (peopleRepository.existsByEmail(person.getEmail())) {
            errors.rejectValue("email", "", "This email is already taken");
        }
        if (person.getDateOfBirth() == null) {
            errors.rejectValue("dateOfBirth", "", "Enter valid date! (dd/MM/yyyy)");
        }
    }
}
