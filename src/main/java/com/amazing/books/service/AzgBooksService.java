package com.amazing.books.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.amazing.books.entity.Book;
import com.amazing.books.entity.Cart;
import com.amazing.books.entity.User;
import com.amazing.books.repository.BookRepository;
import com.amazing.books.repository.CartRepository;
import com.amazing.books.repository.UserRepository;
import com.amazing.books.utils.NameCategory;
import com.amazing.books.utils.StateCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AzgBooksService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartRepository cartRepository;

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
            cart.setTotal(cart.getTotal() + book.getPrice());
        }
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

    public Cart updateCart(Cart cart, Cart foundCart){

        if(foundCart.getState().equals(StateCart.INPROGRESS)){
            //Solo se admiten cambios en carritos en proceso

            if(foundCart.getListBooks().size() > cart.getListBooks().size()){  
                /**
                 * SI antes tenia mas libros que ahora, entonces se sacaron libros
                 * Por eso reseteamos el total
                 */
                cart.setTotal(0.0);
            }
            /**
             * Sumamos el total actual
             */
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


    


}
