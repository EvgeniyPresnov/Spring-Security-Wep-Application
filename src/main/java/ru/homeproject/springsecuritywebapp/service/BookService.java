package ru.homeproject.springsecuritywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homeproject.springsecuritywebapp.entity.Book;
import ru.homeproject.springsecuritywebapp.repository.BookRepository;

import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return (List<Book>) bookRepository.findAll();
    }
}
