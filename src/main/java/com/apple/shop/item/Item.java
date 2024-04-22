package com.apple.shop.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "price", nullable = false)
    private Integer price;

    public Item() {
    }

    public Item(String name, Integer price) {
        this.title = name;
        this.price = price;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        } else if (title.length() > 100) {
            throw new IllegalArgumentException("Title cannot be longer than 100 characters");
        }

        this.title = title;
    }

    public void setPrice(Integer price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

}
