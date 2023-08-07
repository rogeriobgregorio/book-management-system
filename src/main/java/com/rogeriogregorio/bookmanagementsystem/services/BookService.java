package com.rogeriogregorio.bookmanagementsystem.services;

import com.rogeriogregorio.bookmanagementsystem.dto.BookDTO;
import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;

import java.util.List;

public interface BookService {

    public List<BookDTO> findAllBooks();
    public BookDTO findBookById(Long id);
    public List<BookDTO> createBook(BookEntity bookEntity);
    public List<BookDTO> updateBook(BookEntity bookEntity);
    public List<BookDTO> deleteBook(Long id);
    public List<BookDTO> findBookByTitleOrAuthor(String titleOrAuthor);
}
