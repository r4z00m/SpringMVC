//package org.example.my.dao;
//
//import org.example.my.models.Person;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.NoResultException;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PersonDAO {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Person> index() {
//        return sessionFactory
//                .getCurrentSession()
//                .createQuery("SELECT person FROM Person person", Person.class)
//                .getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<Person> show(final int id) {
//        try {
//            Person person = sessionFactory
//                    .getCurrentSession()
//                    .get(Person.class, id);
//            if (person != null) {
//                return Optional.of(person);
//            } else {
//                return Optional.empty();
//            }
//        } catch (NoResultException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<Person> show(final String email) {
//        try {
//            Person person = sessionFactory
//                    .getCurrentSession()
//                    .createQuery("SELECT person FROM Person person WHERE person.email=:personEmail", Person.class)
//                    .setParameter("personEmail", email)
//                    .getSingleResult();
//            return Optional.of(person);
//        } catch (NoResultException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Transactional
//    public void save(Person person) {
//        sessionFactory
//                .getCurrentSession()
//                .save(person);
//    }
//
//    @Transactional
//    public void update(int id, Person person) {
//        Person personToUpdate = sessionFactory
//                .getCurrentSession()
//                .get(Person.class, id);
//        personToUpdate.setName(person.getName());
//        personToUpdate.setAge(person.getAge());
////        personToUpdate.setEmail(person.getEmail());
//        personToUpdate.setAddress(person.getAddress());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Person.class, id));
//    }
//}
