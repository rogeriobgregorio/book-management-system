package com.rogeriogregorio.bookmanagementsystem.services;

import com.rogeriogregorio.bookmanagementsystem.dto.BookDTO;
import com.rogeriogregorio.bookmanagementsystem.entities.Book;
import com.rogeriogregorio.bookmanagementsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<BookDTO> findAll() {

        List<Book> result = bookRepository.findAll();
        List<BookDTO> bookDTOList = result.stream().map(x -> new BookDTO(x)).toList();
        return bookDTOList;
    }
}
