package com.rogeriogregorio.bookmanagementsystem.controllers;

import com.rogeriogregorio.bookmanagementsystem.dto.BookDTO;
import com.rogeriogregorio.bookmanagementsystem.entities.BookEntity;
import com.rogeriogregorio.bookmanagementsystem.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = {"application/json"})
@Tag(name = "Book Management API", description = "API para gerenciamento de livros")
public class BookApiController {

    @Autowired
    private BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Buscar todos", description = "Endpoint para buscar todos os livros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "403", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar livro")
    })
    @GetMapping(value = "/books")
    public ResponseEntity<List<BookDTO>> findAllBooks() {

        return ResponseEntity
                .ok()
                .body(bookService.findAllBooks());
    }

    @Operation(summary = "Buscar por Id",description = "Endpoint para buscar livro pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "403", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar livro")
    })
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<BookDTO> findBookById(@PathVariable Long id) {

        return ResponseEntity
                .ok()
                .body(bookService.findBookById(id));
    }

    @Operation(summary = "Salvar", description = "Endpoint para criar um novo livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro criado com sucesso",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "403", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar livro")
    })
    @PostMapping(value = "/books")
    public ResponseEntity<List<BookDTO>> createBook(@RequestBody @Valid BookEntity bookEntity) {
        bookEntity.setId(null);
        return ResponseEntity
                .ok()
                .body(bookService.createBook(bookEntity));
    }

    @Operation(summary = "Atualizar", description = "Endpoint para editar livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro editado com sucesso",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "403", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao editar livro")
    })
    @PutMapping(value = "/books")
    public ResponseEntity<List<BookDTO>> updateBook(@RequestBody @Valid BookEntity bookEntity) {

        return ResponseEntity
                .ok()
                .body(bookService.updateBook(bookEntity));
    }

    @Operation(summary = "Deletar", description = "Endpoint para deletar livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro deletado com sucesso",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar livro")
    })
    @DeleteMapping(value = "books/{id}")
    public ResponseEntity<List<BookDTO>> deleteBook(@PathVariable Long id) {

        return ResponseEntity
                .ok()
                .body(bookService.deleteBook(id));
    }

    @Operation(summary = "Buscar por título ou autor", description = "Endpoint para buscar livro pelo título ou autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "403", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar livro")
    })
    @GetMapping(value = "books/search")
    public ResponseEntity<List<BookDTO>> findBookByTitleOrAuthor(@RequestParam String titleOrAuthor) {

        return ResponseEntity
                .ok()
                .body(bookService.findBookByTitleOrAuthor(titleOrAuthor));
    }
}
