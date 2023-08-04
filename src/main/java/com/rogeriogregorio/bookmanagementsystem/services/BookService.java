package com.rogeriogregorio.bookmanagementsystem.services;

import com.rogeriogregorio.bookmanagementsystem.dto.BookDTO;
import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;

import java.util.List;

public interface BookService {

    List<BookDTO> findAllBooks();
    BookDTO findBookById(Long id);
    List<BookDTO> createBook(BookEntity bookEntity);
    List<BookDTO> updateBook(BookEntity bookEntity);
    List<BookDTO> deleteBook(Long id);
    List<BookDTO> findBookByTitleOrAuthor(String titleOrAuthor);
}
