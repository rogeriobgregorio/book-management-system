package com.rogeriogregorio.bookmanagementsystem.controllers;

import com.rogeriogregorio.bookmanagementsystem.dto.BookDTO;
import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;
import com.rogeriogregorio.bookmanagementsystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAllBooks() {

        return ResponseEntity
                .ok()
                .body(bookService.findAllBooks());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> findBookById(@PathVariable Long id) {

        return ResponseEntity
                .ok()
                .body(bookService.findBookById(id));
    }

    @PostMapping
    public ResponseEntity<List<BookDTO>> createBook(@RequestBody @Valid BookEntity bookEntity) {

        return ResponseEntity
                .ok()
                .body(bookService.createBook(bookEntity));
    }

    @PutMapping
    public ResponseEntity<List<BookDTO>> updateBook(@RequestBody @Valid BookEntity bookEntity) {

        return ResponseEntity
                .ok()
                .body(bookService.updateBook(bookEntity));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<BookDTO>> deleteBook(@PathVariable Long id) {

        return ResponseEntity
                .ok()
                .body(bookService.deleteBook(id));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<BookDTO>> findBookByTitleOrAuthor(@RequestParam String titleOrAuthor) {

        return ResponseEntity
                .ok()
                .body(bookService.findBookByTitleOrAuthor(titleOrAuthor));
    }
}
