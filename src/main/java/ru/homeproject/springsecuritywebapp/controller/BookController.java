package ru.homeproject.springsecuritywebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.homeproject.springsecuritywebapp.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    private String getBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "/view/books";
    }
}
