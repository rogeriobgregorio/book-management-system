package com.rogeriogregorio.bookmanagementsystem.dto;

import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;

public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private String description;
    private Double price;

    public BookDTO() {
    }

    public BookDTO(BookEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}
