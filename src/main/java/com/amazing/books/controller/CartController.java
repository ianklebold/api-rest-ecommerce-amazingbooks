package com.amazing.books.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.amazing.books.entity.Cart;
import com.amazing.books.service.AzgBooksService;
import com.amazing.books.service.AzgCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/amazingbooks")
public class CartController {

    @Autowired
    AzgBooksService azgBooksService;

    @Autowired
    AzgCartService azgCartService;

    @PostMapping("/v1/newcart")
    public Cart cartNewCart(@RequestBody Cart cart){
        return azgCartService.newCart(cart);
    }
 
    @GetMapping("/v1/cart")
    public ArrayList<Cart> getAllCarts() {
        return azgCartService.getAllCarts();
    }

    @GetMapping("/v1/cart/withbooks")
    public ArrayList<Cart> getAllCartsWhitBooks() {
        return azgCartService.getAllCartsWhitBooks();
    }

    @DeleteMapping("/v1/cart/delete/{id}")
    public void deleteCart(@PathVariable(name="id") Long id ){
        Optional<Cart> cart = azgCartService.getCartById(id);
        azgCartService.deleteCart(cart.get()); 
    }    

    @PutMapping("/v1/cart/update/{id}")
    public Cart updateCart(@PathVariable Long id, 
                                      @RequestBody Cart cart) {
        
        Cart foundCart = azgCartService.getCartById(id).get();
        
        return azgCartService.updateCart(cart, foundCart);
    }
}
