package ru.homeproject.springsecuritywebapp.controller;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.homeproject.springsecuritywebapp.entity.Book;
import ru.homeproject.springsecuritywebapp.exception.BookNotFoundException;
import ru.homeproject.springsecuritywebapp.service.BookService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getListBooksTest() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());

        when(bookService.getBooks()).thenReturn(books);

        mockMvc.perform(get("/books/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/view/books"))
                .andExpect(model().attribute("books", books))
        ;
    }

    @Test
    public void getBookById() throws Exception {
        int id = 1;

        when(bookService.getBookById(id)).thenReturn(new Book());

        mockMvc.perform(get("/books/book/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("/view/book"))
                .andExpect(model().attribute("book", instanceOf(Book.class)))
        ;
    }

    @Test
    public void addNewBookTest() throws Exception {
        doNothing().when(bookService).addBook(new Book());

        mockMvc.perform(get("/books/book/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/view/newBook"))
                .andExpect(model().attribute("book", instanceOf(Book.class)))
        ;
    }

    @Test
    public void editBookByIdTest() throws Exception {
        int id = 1;
        doNothing().when(bookService).updateBookById(id, new Book());

        mockMvc.perform(get("/books/book/edit/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("/view/editBook"))
                .andExpect(model().attribute("book", bookService.getBookById(1)))
        ;

    }

    @Test
    public void deleteBookByIdTest() throws Exception {
        int id = 1;
        doNothing().when(bookService).deleteBookById(id);

        mockMvc.perform(get("/books/book/delete/{id}", id))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/books/list"))
        ;
    }

    @AfterEach
    public void tearDown() {
        mockMvc = null;
    }
}