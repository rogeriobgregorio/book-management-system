package com.rogeriogregorio.bookmanagementsystem.services.impl.test;

import com.rogeriogregorio.bookmanagementsystem.dto.BookDTO;
import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;
import com.rogeriogregorio.bookmanagementsystem.exceptions.BookAlreadyExistsException;
import com.rogeriogregorio.bookmanagementsystem.exceptions.BookNotFoundException;
import com.rogeriogregorio.bookmanagementsystem.repositories.BookRepository;
import com.rogeriogregorio.bookmanagementsystem.services.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        bookService = new BookServiceImpl(bookRepository);
    }


    @Test
    void testFindAllBooks() {
        List<BookEntity> bookEntities = new ArrayList<>();
        bookEntities.add(new BookEntity("Title 1", "Author 1", "Description 1", 10.0));
        when(bookRepository.findAll()).thenReturn(bookEntities);

        List<BookDTO> bookDTOs = bookService.findAllBooks();

        assertEquals(1, bookDTOs.size());
    }

    @Test
    void testFindAllBooksNotFound() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(BookNotFoundException.class, () -> bookService.findAllBooks());
    }



    @Test
    void testFindBookById() {
        Long id = 1L;
        BookEntity bookEntity = new BookEntity("Title 1", "Author 1", "Description 1", 10.0);
        when(bookRepository.findById(id)).thenReturn(Optional.of(bookEntity));

        BookDTO bookDTO = bookService.findBookById(id);

        assertNotNull(bookDTO);
    }

    @Test
    void testFindBookByIdNotFound() {
        Long id = 1L;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.findBookById(id));
    }

    @Test
    public void testCreateBook() {
        BookEntity bookEntity = new BookEntity("Title", "Author", "Description", 20.0);
        List<BookEntity> bookEntities = Collections.singletonList(bookEntity);

        when(bookRepository.findAll()).thenReturn(bookEntities);

        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);

        List<BookDTO> createdBooks = bookService.createBook(bookEntity);

        assertNotNull(createdBooks);
        assertFalse(createdBooks.isEmpty());
        assertEquals(1, createdBooks.size());
    }

    @Test
    void testCreateBookAlreadyExists() {
        BookEntity bookEntity = new BookEntity("Title", "Author", "Description", 20.0);

        when(bookRepository.existsById(bookEntity.getId())).thenReturn(true);

        assertThrows(BookAlreadyExistsException.class, () -> bookService.createBook(bookEntity));
    }

    @Test
    public void testUpdateBook() {
        BookEntity bookEntity = new BookEntity("Title", "Author", "Description", 20.0);
        List<BookEntity> bookEntities = Collections.singletonList(bookEntity);

        when(bookRepository.findAll()).thenReturn(bookEntities);

        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);

        List<BookDTO> createdBooks = bookService.createBook(bookEntity);

        assertNotNull(createdBooks);
        assertFalse(createdBooks.isEmpty());
        assertEquals(1, createdBooks.size());
    }

    @Test
    void testUpdateBookNotFound() {
        BookEntity bookEntity = new BookEntity("Title", "Author", "Description", 20.0);

        when(bookRepository.existsById(bookEntity.getId())).thenReturn(false);

        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(bookEntity));
    }

    @Test
    public void testDeleteBook() {
        Long id = 1L;

        BookEntity bookToDelete = new BookEntity("Title", "Author", "Description", 20.0);
        List<BookEntity> bookEntities = Collections.singletonList(bookToDelete);

        when(bookRepository.existsById(id)).thenReturn(true);
        when(bookRepository.findById(id)).thenReturn(Optional.of(bookToDelete));
        when(bookRepository.findAll()).thenReturn(bookEntities);

        List<BookDTO> deletedBooks = bookService.deleteBook(id);

        assertNotNull(deletedBooks);
        assertFalse(deletedBooks.isEmpty());

        verify(bookRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteBookNotFound() {
        Long id = 1L;

        when(bookRepository.existsById(id)).thenReturn(false);

        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(id));
    }

    @Test
    void testFindBookByTitleOrAuthor() {
        String searchTerm = "Title";

        List<BookEntity> bookEntities = new ArrayList<>();
        bookEntities.add(new BookEntity("Title 1", "Author 1", "Description 1", 10.0));
        bookEntities.add(new BookEntity("Title 2", "Author 2", "Description 2", 15.0));

        when(bookRepository.findByTitleOrAuthor("%" + searchTerm + "%")).thenReturn(bookEntities);

        List<BookDTO> bookDTOs = bookService.findBookByTitleOrAuthor(searchTerm);

        assertEquals(2, bookDTOs.size());
    }
}

