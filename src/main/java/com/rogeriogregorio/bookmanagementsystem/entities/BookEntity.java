package com.rogeriogregorio.bookmanagementsystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.mapping.Value;

import java.util.Objects;

@Entity
@Table(name = "tb_books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "O título não pode estar em branco")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "O autor não pode estar em branco")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O autor deve conter apenas letras e espaços")
    @Column(name = "author")
    private String author;

    @NotBlank(message = "A descrição não pode estar em branco")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "O preço não pode ser nulo")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0")
    @Column(name = "price")
    private Double price;

    public BookEntity() {
    }

    public BookEntity(String title, String author,
                      String description, Double price) {
        this.title = title;
        this.author = author;
        this.description = description;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
