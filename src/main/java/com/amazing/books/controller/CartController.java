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
        /**
         * Creacion de un nuevo carrito.
         *  -> Se puede crear un carrito, cargar los libros y cerrarlo.
         *  -> No se permiten cerrar carritos sin libros.
         *  -> Se admite solo un carrito en proceso por usuario
         *  -> Se admiten multiples carritos cerrados por usuarios.
         * 
         */
        return azgCartService.newCart(cart);
    }
 
    @GetMapping("/v1/cart")
    public ArrayList<Cart> getAllCarts() {
        /**
         * Obtener todos los carritos
         */
        return azgCartService.getAllCarts();
    }

    @GetMapping("/v1/cart/withbooks")
    public ArrayList<Cart> getAllCartsWhitBooks() {
        return azgCartService.getAllCartsWhitBooks();
    }

    @DeleteMapping("/v1/cart/delete/{id}")
    public void deleteCart(@PathVariable(name="id") Long id ){
        /**
         * Eliminamos un carrito
         */
        Optional<Cart> cart = azgCartService.getCartById(id);
        azgCartService.deleteCart(cart.get()); 
    }    

    @PutMapping("/v1/cart/update/{id}")
    public Cart updateCart(@PathVariable Long id, 
                                      @RequestBody Cart cart) {
        /**
         * Actualizacion de carrito.
         *  -> Se puede aumentar la cantidad de libros y decrementar la cantidad de libros
         *  -> Se puede cargar mas de una vez un libro (Varias compras de un mismo producto)
         *  -> Al aumentar libros o decrementar se actualiza automaticamente el precio total 
         *  
         *  **Nota : Solo se admite actualizacion de carritos en proceso.
         */
        
        Cart foundCart = azgCartService.getCartById(id).get();
        
        return azgCartService.updateCart(cart, foundCart);
    }
}
