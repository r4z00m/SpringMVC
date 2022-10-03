package org.example.my.dao;

import org.example.my.models.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testN1() {
        Session session = entityManager.unwrap(Session.class);

        List<Person> list = session
                .createQuery("select p from Person p", Person.class)
                .getResultList();

        for (Person person : list) {
            System.out.println(person.getItems());
        }
    }

    @Transactional(readOnly = true)
    public void testN2() {
        Session session = entityManager.unwrap(Session.class);

        Set<Person> list = new HashSet<>(session
                .createQuery("select p from Person p join fetch p.items", Person.class)
                .getResultList());

        for (Person person : list) {
            System.out.println(person.getItems());
        }
    }
}
