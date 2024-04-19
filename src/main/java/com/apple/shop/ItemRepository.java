package com.apple.shop;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long>{
    Optional<Item> findByTitle(String title);
}
