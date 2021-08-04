package com.amazing.books.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.amazing.books.entity.User;
import com.amazing.books.service.AzgBooksService;
import com.amazing.books.service.AzgUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/amazingbooks")
public class UserController {
    @Autowired
    AzgBooksService azgBooksService;

    @Autowired
    AzgUserService azgUserService;


    @PostMapping("/v1/newuser")
    public User postNewUser(@RequestBody User user) {
        /**
         * Para crear un nuevo usuario
         */
        
        return azgUserService.newUser(user);
    }

    @GetMapping("/v1/user")
    public ArrayList<User> getAllUser() {
        /**
         * Obtener todos los usuarios
         */
        return azgUserService.getAllUsers();
    }

    @DeleteMapping("/v1/user/delete/{id}")
    public void deleteUser(@PathVariable(name="id") Long id ){
        /**
         * Eliminar un usuario
         * 
         *  --> Eliminar un usuario implica eliminar todos sus carritos.
         */
        Optional<User> user = azgUserService.getUserById(id);
        azgUserService.deleteUser(user.get());
    }

    @PutMapping(value="/v1/user/update/{id}")
    public User updateUser(@PathVariable(name = "id") Long id, @RequestBody User user) {
        /**
         * Actualizar los datos de un usuario
         * 
         * --> La actualizacion de un usuario implica actualizar los datos a sus
         *     Entidades que dependan de él.
         */
        
        Optional<User> foundUser = azgUserService.getUserById(id);
        
        return azgUserService.updateUser(user, foundUser);

    }
    
    
    

}
