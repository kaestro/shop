package com.apple.shop.board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Board {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "글제목")
    private String title;
    @Column(name = "날짜")
    private java.time.LocalDate date;

    public Board() {
    }

    public Board(String title, java.time.LocalDate date) {
        this.title = title;
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(java.time.LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public java.time.LocalDate getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }
}
