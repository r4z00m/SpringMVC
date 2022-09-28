package org.example.my.services;

import org.example.my.models.Item;
import org.example.my.models.Person;
import org.example.my.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findByItemName(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> findByPerson(Person person) {
        return itemRepository.findByPerson(person);
    }
}
