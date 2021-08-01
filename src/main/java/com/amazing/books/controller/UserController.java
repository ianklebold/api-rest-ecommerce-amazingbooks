package com.amazing.books.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.amazing.books.entity.User;
import com.amazing.books.service.AzgBooksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/amazingbooks")
public class UserController {
    @Autowired
    AzgBooksService azgBooksService;


    @PostMapping("/v1/newuser")
    public User postNewUser(@RequestBody User user) {
        
        return azgBooksService.newUser(user);
    }

    @GetMapping("/v1/user")
    public ArrayList<User> getAllUser() {
        return azgBooksService.getAllUsers();
    }

    @DeleteMapping("/v1/user/delete/{id}")
    public void deleteUser(@PathVariable(name="id") Long id ){
        //Find user
        Optional<User> user = azgBooksService.getUserById(id);
        azgBooksService.deleteUser(user.get());
    }

    @PutMapping(value="/v1/user/update/{id}")
    public User updateUser(@PathVariable(name = "id") Long id, @RequestBody User user) {
        
        Optional<User> foundUser = azgBooksService.getUserById(id);
        
        return azgBooksService.updateUser(user, foundUser);

    }
    
    
    

}
