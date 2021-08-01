package com.amazing.books.service;

import java.util.ArrayList;
import java.util.Optional;

import com.amazing.books.entity.Book;
import com.amazing.books.entity.User;
import com.amazing.books.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AzgBooksService {
    
    @Autowired
    UserRepository userRepository;

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
