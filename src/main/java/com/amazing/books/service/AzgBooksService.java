package com.amazing.books.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import com.amazing.books.entity.Book;
import com.amazing.books.entity.User;
import com.amazing.books.repository.BookRepository;
import com.amazing.books.repository.UserRepository;
import com.amazing.books.utils.NameCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AzgBooksService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    /* METHODS FOR BOOK!!! */

    public Book newBook(Book book){

        if((controlCategory(String.valueOf(book.getCategory()))) == false){
            book.setCategory(null);
            //Error no se puede mandar una categoria distinta.
        }

        return bookRepository.save(book);
    }

    public ArrayList<Book> getAllBooks(){
        return (ArrayList<Book>) bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    public Book updateBook(Book book, Optional<Book> foundBook){

        //Control Category
        if ( (controlCategory(String.valueOf(book.getCategory()))) == false ){
            book.setCategory(foundBook.get().getCategory());
        }

        book.setPrice(foundBook.get().getPrice());
        book.setInventoryCode(foundBook.get().getInventoryCode());

        return bookRepository.save(book);

    }

    public Boolean controlCategory(String newCategory){
        ArrayList<String> listCategory = new ArrayList<String>(); 
   
        for (NameCategory category : Arrays.asList(NameCategory.values())) {
            listCategory.add(String.valueOf(category));   
        }

        return listCategory.contains(newCategory);
        
    }

    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }





    /* METHODS FOR USER!!! */
    public User newUser(User user){
        return userRepository.save(user);
    }

    public ArrayList<User> getAllUsers(){
        return (ArrayList<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public User updateUser(User user, Optional<User> foundUser){
        
    
        user.setCreatedDate(foundUser.get().getCreatedDate());

        return userRepository.save(user);
    }

    


}
