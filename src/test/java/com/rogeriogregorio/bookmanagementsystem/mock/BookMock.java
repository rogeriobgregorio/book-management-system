package com.rogeriogregorio.bookmanagementsystem.mock;

import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;

import java.util.List;

public class BookMock {

    public static BookEntity getBookEntity() {

        BookEntity book = new BookEntity();

        book.setTitle("O Pequeno Príncipe");
        book.setAuthor("Antoine de Saint-Exupéry");
        book.setDescription("Um menino que mora num planetinha");
        book.setPrice(19.90);

        return book;
    }

    public static List<BookEntity> getListBookEntity() {
        return List.of(getBookEntity());
    }
}
