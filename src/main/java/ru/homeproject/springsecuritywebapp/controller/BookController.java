package ru.homeproject.springsecuritywebapp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.homeproject.springsecuritywebapp.entity.Book;
import ru.homeproject.springsecuritywebapp.service.BookService;
import ru.homeproject.springsecuritywebapp.validation.BookValidator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static ru.homeproject.springsecuritywebapp.util.BookControllerConstants.*;

/**
 * This class handles the requests which the URL ends with "/books".
 *
 */

@Controller
@RequestMapping("/books")
@Log4j2
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    @PostConstruct
    private void startLogging() {
        log.info(START_LOGGING);
    }

    /**
     * The method adds a custom validator for validating input data on a form.
     *
     * @param binder
     */
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(bookValidator);
    }

    /**
     * The method handles the request for displaying books.
     *
     * @param model
     * @return the form that displays books
     */
    @GetMapping("/list")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
        log.info(String.format(LIST_BOOKS, bookService.getBooks()));
        return "/view/books";
    }

    /**
     * The method handles the request for displaying the book by id.
     *
     * @param id
     * @param model
     * @return the form that displays the book by id
     */
    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        log.info(String.format(GET_BOOK, bookService.getBookById(id)));
        return "/view/book";
    }

    /**
     * The method handles the request for adding a new book.
     *
     * @param book
     * @return the edit form
     */
    @GetMapping("/book/add")
    public String addNewBook(@ModelAttribute("book") Book book) {
        return "/view/newBook";
    }


    /**
     * The method invokes for filling book's data on the form.
     *
     * @param book
     * @return the form that displays books
     */
    @PostMapping("")
    public String newBook(@ModelAttribute("book") @Validated Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn(INVALID_INPUT_DATA);
            return "/view/newBook";
        }
        bookService.addBook(book);
        log.info(ADD_NEW_NOOK, book);
        return "redirect:/books/list";
    }

    /**
     * The method handles the request for editing the book by id.
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/book/edit/{id}")
    public String editToUpdateBookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "/view/editBook";
    }

    /**
     * The method invokes for filling book's data to edit one by id.
     *
     * @param id
     * @param book
     * @return the form that displays boo
     */
    @PostMapping("/book/{id}")
    public String updateBookById(@PathVariable("id") int id,
                                 @ModelAttribute("book") @Validated Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn(INVALID_INPUT_DATA);
            return "/view/editBook";
        }
        bookService.updateBookById(id, book);
        log.info(String.format(EDIT_BOOK, book, id));
        return "redirect:/books/list";
    }

    /**
     * The method handles the request for deleting the book by id.
     *
     * @param id
     * @return the form that displays books
     */
    @GetMapping("/book/delete/{id}")
    public String deleteBookById(@PathVariable("id") int id) {
        log.info(String.format(DELETE_BOOK, bookService.getBookById(id)));
        bookService.deleteBookById(id);
        return "redirect:/books/list";
    }

    /**
     * The method handles the request for getting information about web application.
     *
     * @return information about web application
     */
    @GetMapping("/info")
    public String infoPage() {
        return "info";
    }

    @PreDestroy
    private void stopLogging() {
        log.info(STOP_LOGGING);
    }
}
