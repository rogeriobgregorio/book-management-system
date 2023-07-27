package com.rogeriogregorio.bookmanagementsystem.services;

import com.rogeriogregorio.bookmanagementsystem.dto.BookDTO;
import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;
import com.rogeriogregorio.bookmanagementsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<BookDTO> findAllBooks() {

        return bookRepository
                .findAll()
                .stream()
                .map(BookDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookDTO findBookById(Long id) {

        return new BookDTO(bookRepository.findById(id).get());
    }

    @Transactional(readOnly = false)
    public List<BookDTO> createBook(BookEntity bookEntity) {

        bookRepository.save(bookEntity);
        return findAllBooks();
    }

    @Transactional(readOnly = false)
    public List<BookDTO> updateBook(BookEntity bookEntity) {

        bookRepository.save(bookEntity);
        return findAllBooks();
    }

    @Transactional(readOnly = false)
    public List<BookDTO> deleteBook(Long id) {

        bookRepository.deleteById(id);
        return findAllBooks();
    }

    @Transactional(readOnly = true)
    public List<BookDTO> findBookByTitleOrAuthor(String titleOrAuthor) {
        String searchTerm = "%" + titleOrAuthor + "%";
        return bookRepository.findByTitleOrAuthor(searchTerm)
                .stream()
                .map(BookDTO::new)
                .toList();
    }
}