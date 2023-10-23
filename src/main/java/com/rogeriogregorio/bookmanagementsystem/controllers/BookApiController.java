package com.rogeriogregorio.bookmanagementsystem.controllers;

import com.rogeriogregorio.bookmanagementsystem.dto.BookDTO;
import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;
import com.rogeriogregorio.bookmanagementsystem.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class BookApiController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books")
    public ResponseEntity<List<BookDTO>> findAllBooks() {

        return ResponseEntity
                .ok()
                .body(bookService.findAllBooks());
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<BookDTO> findBookById(@PathVariable Long id) {

        return ResponseEntity
                .ok()
                .body(bookService.findBookById(id));
    }

    @PostMapping(value = "/books")
    public ResponseEntity<List<BookDTO>> createBook(@RequestBody @Valid BookEntity bookEntity) {
        bookEntity.setId(null);
        return ResponseEntity
                .ok()
                .body(bookService.createBook(bookEntity));
    }

    @PutMapping(value = "/books")
    public ResponseEntity<List<BookDTO>> updateBook(@RequestBody @Valid BookEntity bookEntity) {

        return ResponseEntity
                .ok()
                .body(bookService.updateBook(bookEntity));
    }

    @DeleteMapping(value = "books/{id}")
    public ResponseEntity<List<BookDTO>> deleteBook(@PathVariable Long id) {

        return ResponseEntity
                .ok()
                .body(bookService.deleteBook(id));
    }

    @GetMapping(value = "books/search")
    public ResponseEntity<List<BookDTO>> findBookByTitleOrAuthor(@RequestParam String titleOrAuthor) {

        return ResponseEntity
                .ok()
                .body(bookService.findBookByTitleOrAuthor(titleOrAuthor));
    }
}
