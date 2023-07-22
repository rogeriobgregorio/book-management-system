package com.rogeriogregorio.bookmanagementsystem.controllers;

import com.rogeriogregorio.bookmanagementsystem.dto.BookDTO;
import com.rogeriogregorio.bookmanagementsystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public List<BookDTO> findAll() {

        List<BookDTO> bookDTOList = bookService.findAll();
        return bookDTOList;
    }
}
