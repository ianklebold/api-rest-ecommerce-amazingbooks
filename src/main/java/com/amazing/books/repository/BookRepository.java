package com.amazing.books.repository;

import java.util.ArrayList;

import com.amazing.books.entity.Book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
    ArrayList<Book> findByNameStartingWith(String nombre);
}
