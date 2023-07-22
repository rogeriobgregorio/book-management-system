package com.rogeriogregorio.bookmanagementsystem.dto;

import com.rogeriogregorio.bookmanagementsystem.entities.Book;
import jakarta.persistence.Column;

public class BookDTO {

    private String title;
    private String author;
    private String description;
    private Double price;

    public BookDTO() {
    }

    public BookDTO(Book entity) {
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
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
