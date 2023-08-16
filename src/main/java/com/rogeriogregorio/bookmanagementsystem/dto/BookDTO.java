package com.rogeriogregorio.bookmanagementsystem.dto;

import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BookDTO {

    private Long id;

    @NotBlank(message = "O título não pode estar em branco")
    private String title;

    @NotBlank(message = "O autor não pode estar em branco")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O autor deve conter apenas letras")
    private String author;

    @NotBlank(message = "A descrição não pode estar em branco")
    private String description;

    @NotNull(message = "O preço não pode ser nulo")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0")
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
