package com.apple.shop;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> findAll();
    Optional<Item> findById(Long id);
    Optional<Item> findByTitle(String title);
    Item save(Item item);
    Long count();
    Item update(Long id, Item item);
    void delete(Long id);
}