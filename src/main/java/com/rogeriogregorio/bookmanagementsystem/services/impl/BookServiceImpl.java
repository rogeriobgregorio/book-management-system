package com.rogeriogregorio.bookmanagementsystem.services.impl;

import com.rogeriogregorio.bookmanagementsystem.dto.BookDTO;
import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;
import com.rogeriogregorio.bookmanagementsystem.exceptions.*;
import com.rogeriogregorio.bookmanagementsystem.repositories.BookRepository;
import com.rogeriogregorio.bookmanagementsystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<BookDTO> findAllBooks() {

        if (bookRepository.findAll().isEmpty()){
            throw new BookNotFoundException("Nenhum livro encontrado.");
        }
        return bookRepository
                .findAll()
                .stream()
                .map(BookDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookDTO findBookById(Long id) {

        return new BookDTO(bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException("Livro não encontrado com o ID: " + id + ".")));
    }

    @Transactional(readOnly = false)
    public List<BookDTO> createBook(@Valid BookEntity bookEntity) {

        if (bookRepository.existsById(bookEntity.getId())) {
            throw new BookAlreadyExistsException("Livro já existe com o ID: " + bookEntity.getId() + ".");
        }

        try {
            bookRepository.save(bookEntity);
        } catch (Exception e) {
            throw new BookCreationException("Erro ao criar o livro: " + e.getMessage() + ".");
        }

        return findAllBooks();
    }

    @Transactional(readOnly = false)
    public List<BookDTO> updateBook(@Valid BookEntity bookEntity) {

        if (!bookRepository.existsById(bookEntity.getId())) {
            throw new BookNotFoundException("Livro não encontrado com o ID: " + bookEntity.getId() + ".");
        }

        try {
            bookRepository.save(bookEntity);
        } catch (Exception e) {
            throw new BookUpdateException("Erro ao atualizar o livro: " + e.getMessage() + ".");
        }

        return findAllBooks();
    }

    @Transactional(readOnly = false)
    public List<BookDTO> deleteBook(Long id) {

        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Livro não encontrado com o ID: " + id + ".");
        }

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