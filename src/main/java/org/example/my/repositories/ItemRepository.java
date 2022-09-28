package org.example.my.repositories;

import org.example.my.models.Item;
import org.example.my.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByName(String name);
    List<Item> findByPerson(Person person);
}
