package com.amazing.books.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.amazing.books.entity.Book;
import com.amazing.books.service.AzgBooksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/amazingbooks")
public class BookController {
    @Autowired
    AzgBooksService azgBooksService;

    @PostMapping("/v1/newbook")
    public Book postNewBook(@RequestBody Book book) {
        
        return azgBooksService.newBook(book);
    }

    @GetMapping("/v1/book")
    public ArrayList<Book> getAllBooks() {
        return azgBooksService.getAllBooks();
    }

    @DeleteMapping("/v1/book/delete/{id}")
    public void deleteBook(@PathVariable(name="id") Long id ){
        
        Optional<Book> book = azgBooksService.getBookById(id);
        azgBooksService.deleteBook(book.get());

    }

    @PutMapping("/v1/book/update/{id}")
    public Book updateBook(@PathVariable(name = "id") Long id, 
                           @RequestBody Book book) {
       
        Optional<Book> foundBook = azgBooksService.getBookById(id);
        return azgBooksService.updateBook(book, foundBook);
    }

    @GetMapping("/v1/book/{id}")
    public Book findBookById(@PathVariable(name = "id") Long id) {
       
        return azgBooksService.findBookById(id).get();
    }
    

    

    
}
