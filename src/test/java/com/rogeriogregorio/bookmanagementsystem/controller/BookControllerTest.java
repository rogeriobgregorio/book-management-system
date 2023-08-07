package com.rogeriogregorio.bookmanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogeriogregorio.bookmanagementsystem.controllers.BookController;
import com.rogeriogregorio.bookmanagementsystem.mock.BookMock;
import com.rogeriogregorio.bookmanagementsystem.repositories.BookRepository;
import com.rogeriogregorio.bookmanagementsystem.services.BookService;
import com.rogeriogregorio.bookmanagementsystem.services.impl.BookServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest({
        BookController.class,
        BookService.class,
        BookServiceImpl.class,
        BookRepository.class,
        ObjectMapper.class
})
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookRepository bookRepository;

    @Test
    @Order(10)
    void deveRetornarBookListIsOk() throws Exception {
        Mockito.doReturn(BookMock.getListBookEntity())
                         .when(bookRepository).findAll();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books")
                        //.get("/books/{bookid}", 1)
                        //.param("codigoEstabelecimento", "2")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$[0].title")
                                .value("O Pequeno Príncipe"))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$[0].author")
                                .value("Antoine de Saint-Exupéry"))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$[0].description")
                                .value("Um menino que mora num planetinha"))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$[0].price")
                                .value(19.90))
                        .andDo(print());
    }
}