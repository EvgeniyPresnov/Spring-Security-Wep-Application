package ru.homeproject.springsecuritywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homeproject.springsecuritywebapp.entity.Book;
import ru.homeproject.springsecuritywebapp.exception.BookNotFoundException;
import ru.homeproject.springsecuritywebapp.repository.BookRepository;

import java.util.List;
import java.util.Optional;

/**
 * This class provides the business logic about books.
 *
 */

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * The method returns the books from database.
     *
     * @return the list of books
     */
    public List<Book> getBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    /**
     * The methods returns the book by id.
     *
     * @param id
     * @return the book by id
     */
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    /**
     * The methods returns the book by id.
     *
     * @param id
     * @return the book by id
     */
//    public Book getBookById(int id, Book book) {
//        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id, book));
//    }

    /**
     * The method adds a new book to database.
     *
     * @param book
     */
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * The method updates book's data by id.
     *
     * @param id
     * @param book
     */
    public void updateBookById(int id, Book book) {
        Book updatedBook = getBookById(id);
        updatedBook.setId(book.getId());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setTitle(book.getTitle());
        updatedBook.setPrice(book.getPrice());
        bookRepository.save(updatedBook);
    }

    /**
     *  The method deletes the book by id from database.
     *
     * @param id
     */
    public void deleteBookById(int id)  {
        bookRepository.deleteById(getBookById(id).getId());
    }
}
