package ru.homeproject.springsecuritywebapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.homeproject.springsecuritywebapp.entity.Book;

@Repository
public interface BookRepository extends CrudRepository <Book, Integer> {

}
