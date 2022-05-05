package ru.homeproject.springsecuritywebapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.homeproject.springsecuritywebapp.entity.Book;
import ru.homeproject.springsecuritywebapp.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookService bookService;

    @Captor
    private ArgumentCaptor<Book> captor;

    private List<Book> listBooks;

    @BeforeEach
    public void setUp() {
        listBooks = new ArrayList<Book>() {
            {
                add(new Book(1, "George Orwell", "1984", 100.32));
                add(new Book(2, "J.R.R. Tolkien", "The Lord of the Rings", 43.21));
                add(new Book(3,"Harper Lee", "To Kill a Mockingbird", 66.23));
            }
        };
    }

    @Test
    public void getBooksTest() {
        when(bookService.getBooks()).thenReturn(listBooks);
        assertTrue(bookService.getBooks().size() > 0);
        verify(bookService).getBooks();
    }

    @Test
    public void getBookByIdTest() {
        int id = 0;
        when(bookService.getBookById(id))
                .thenReturn(new Book(1, "George Orwell", "1984", 100.32));

        String titleBookExpected = new Book(1, "George Orwell", "1984", 100.32).getTitle();
        String titleBookActual = bookService.getBookById(id).getTitle();
        assertEquals(titleBookExpected, titleBookActual);
        verify(bookService).getBookById(id);
    }

    @Test
    public void getBookByIdNotFound() {
        int id = 100;
        assertThrows(Exception.class, () -> listBooks.get(id));

        when(bookService.getBookById(id)).thenThrow(BookNotFoundException.class);
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(id));
        verify(bookService).getBookById(id);
    }

    @Test
    public void addNewBookTest() {
        Book newBook = new Book(4, "F. Scott Fitzgerald", "The Great Gatsby", 52.43);
        String newBookTitle = newBook.getTitle();

        doNothing().when(bookService).addBook(captor.capture());
        bookService.addBook(newBook);

        verify(bookService, times(1)).addBook(newBook);
        assertEquals(newBookTitle, captor.getValue().getTitle());
    }

    @Test
    public void updateBookByIdTest() {
        int id = 1;
        Book updatedBook = listBooks.get(id);
        updatedBook.setAuthor("Jane Austen");
        updatedBook.setTitle("Pride and Prejudice");
        updatedBook.setPrice(43.23);

        assertNotEquals(bookService.getBookById(id), updatedBook);

        doNothing().when(bookService).updateBookById(id, updatedBook);
        bookService.updateBookById(id, updatedBook);
        verify(bookService).updateBookById(id, updatedBook);
    }

    @Test
    public void deleteBookByIdTest() {
        int id = 1;

        doNothing().when(bookService).deleteBookById(id);
        bookService.deleteBookById(id);
        verify(bookService).deleteBookById(id);

        int outLimitedId = 100;
        doThrow(new BookNotFoundException(outLimitedId)).when(bookService).deleteBookById(outLimitedId);
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBookById(outLimitedId));
    }

}