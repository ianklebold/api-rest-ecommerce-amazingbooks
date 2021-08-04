package com.amazing.books.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import com.amazing.books.entity.Book;
import com.amazing.books.entity.Cart;
import com.amazing.books.entity.User;
import com.amazing.books.repository.BookRepository;
import com.amazing.books.repository.CartRepository;
import com.amazing.books.repository.UserRepository;
import com.amazing.books.utils.StateCart;

import org.springframework.beans.factory.annotation.Autowired;



@Service
public class AzgCartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;


    /* METHODS FOR CART!!! */

    public Cart newCart(Cart cart){
        /**
         * Creacion de un nuevo carrito
         */
        
        if(cart.getState().equals(StateCart.CLOSED)){
            /**
             * Si el nuevo carrito pasa al estado cerrado
             */
            if(cart.getListBooks().size() == 0){
                /**
                 * No existe carrito cerrado sin libros
                 */
                return null;
            }else{
                /**
                 * Se suma todo y se guarda
                 */
                sumTotal(cart);
                return cartRepository.save(cart);
            }
        }else{
            /**
             * Si el carrito esta en progreso
             * Controlamos que solo haya un carrito en progreso
             */

            if(controlStateCart(cart) == true){
                /**
                 * Si solo hay uno solo, se suma todo y se guarda
                 */
                sumTotal(cart);
                return cartRepository.save(cart);
            }else{
                /**
                 * Sino no se guarda
                 */
                return null;
            }
            
        }
        
        
    }

    public Boolean controlStateCart(Cart cart){
        /**
         * Se encarga del control del carrito
         */
        Boolean key = true;
        Integer count = 0;
        
        User user = userRepository.findById(cart.getUser().getId()).get();
        System.out.println(user.getCart().size());

        if(user.getCart().size() ==  0){
            /**
             * Si es el primer carrito del usuario, todo bien.
             */
            
            return key;
        }else{
            for (Cart carritos  : user.getCart()) {
                /**
                 * Si no es el primero, iteramos buscando si hay carrito "InProgress"
                 */
                if(carritos.getState().equals(StateCart.INPROGRESS)){
                    
                    count = count + 1;
                }
            }
            
        } 
        if(count > 0){
            /**
             * Hay carrito en progreso, retorna falso 
             * */
            key = false;
        }
        return key;
    }

    public void sumTotal(Cart cart){
        //Iteramos y obtenemos el total final
        for (Book book : cart.getListBooks()) {
            /**
             * Ahora nos aseguramos que se mantenga el precio de lista
             *  y no se lo pueda cambiar
             */
            Book bookaux = findBookById(book.getId()).get();
            book.setPrice(bookaux.getPrice());
            /**
             * Seteamos el precio final
             */
            cart.setTotal(cart.getTotal() + book.getPrice());
        }
    }

    public Cart updateCart(Cart cart, Cart foundCart){

        if(foundCart.getState().equals(StateCart.INPROGRESS)){
            //Solo se admiten cambios en carritos en proceso

            
            /**
             * Sumamos el total actual
             */
            cart.setTotal(0.0);
            sumTotal(cart);
            //La fecha y usuario no se puede cambiar
            cart.setCreatedDate(foundCart.getCreatedDate());
            cart.setUser(foundCart.getUser());

            if(cart.getState().equals(StateCart.CLOSED) && cart.getListBooks().size() == 0){
                /**
                 * Si actualizamos estado y sacamos todos los libros retornamos nulo o error
                 */
                return null;
            }else{
                /**
                 * Se guardan los cambios
                 */
                return cartRepository.save(cart);
            }            
        }else{
            //Sino no se realizan cambios
            return cartRepository.save(foundCart);
        }
    }

    // Metodos para peticiones!!!
    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }

    public ArrayList<Cart> getAllCartsWhitBooks(){
        return (ArrayList<Cart>) cartRepository.findAll();
    }


    public ArrayList<Cart> getAllCarts(){
        return (ArrayList<Cart>) cartRepository.findAll();

    }

    public Optional<Cart> getCartById(Long id){
        return cartRepository.findById(id);
    }

    public void deleteCart(Cart cart){
        cartRepository.delete(cart);
    }


    
}
