package com.amazing.books.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.amazing.books.entity.Book;
import com.amazing.books.entity.Cart;
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
        /**
         * Creacion de un libro
         */

        if((controlCategory(String.valueOf(book.getCategory()))) == false){
            book.setCategory(null);
            //Error no se puede mandar una categoria distinta.
        }

        return bookRepository.save(book);
    }



    public void deleteBook(Book book){
        ArrayList<Cart> listCarritos = (ArrayList<Cart>) cartRepository.findAll(); 
        
        for (Cart cart : listCarritos) {
            if(cart.getState().equals(StateCart.INPROGRESS)){
                //Obtenemos los libros de un carrito.
                List<Book> listaCart = cart.getListBooks();
                
                for (int i = 0; i < listaCart.size(); i++){
                    Boolean foundIds = false;
                    if(book.getId().equals(listaCart.get(i).getId())){
                        //Controlamos si el libro a eliminar esta en algun carrito
                        foundIds = true;
                    }

                    if(foundIds){
                       /**
                        *Damos de baja libro de carrito y 
                        *Actualizamos su precio (Only inProgess) 
                        */
                        cart.setTotal(cart.getTotal() - book.getPrice());
                        cart.getListBooks().remove(book);
                        cartRepository.save(cart);
                    }
                    
           
                }
            
            }

        }
        //Eliminamos libro
        bookRepository.delete(book);
    }

    public Book updateBook(Book book, Optional<Book> foundBook){

        //Control Category
        if ( (controlCategory(String.valueOf(book.getCategory()))) == false ){
            book.setCategory(foundBook.get().getCategory());
        }
        
        if(book.getPrice() != foundBook.get().getPrice()){
            /**
             * Metodo para actualizar precios de carritos con libro actualizado
             */
            updateTotalCartForBooks(book, foundBook);
        }
        
        //Codigo de producto no se puede cambiar
        book.setInventoryCode(foundBook.get().getInventoryCode());

        return bookRepository.save(book);

    }
    
    public void updateTotalCartForBooks(Book book, Optional<Book> foundBook){
        
        ArrayList<Cart> listCarritos = (ArrayList<Cart>) cartRepository.findAll(); 


        for (Cart cart : listCarritos) {
            //Preguntamos si el carrito esta en progreso
            if(cart.getState().equals(StateCart.INPROGRESS)){
                //Si es asi obtengo su lista de libros
                List<Book> listaCart = cart.getListBooks();

                for (int i = 0; i < listaCart.size(); i++){
                   
                    Boolean foundIds = false;
                    if(book.getId().equals(listaCart.get(i).getId())){                        
                        //Preguntamos si el libro actulizado esta en la lista
                        foundIds = true;
                    }

                    if(foundIds){
                        if(foundBook.get().getPrice() > book.getPrice()){

                            //El precio disminuyó, disminuye el precio en cada carrito.
                            cart.setTotal(cart.getTotal() - foundBook.get().getPrice());

                            cart.setTotal(cart.getTotal() + book.getPrice());
                            
                        }else if(foundBook.get().getPrice() < book.getPrice()){

                            //El precio aumentó, aumenta el precio en cada carrito
                            cart.setTotal(cart.getTotal() - foundBook.get().getPrice());
                            
                            cart.setTotal(cart.getTotal() + book.getPrice());

                        }
                        cartRepository.save(cart); 
                    }
                }  
            }

        }
       

    }

    public Boolean controlCategory(String newCategory){
        /**
         * Un metodo que sirve para controlar la categoria
         */
        ArrayList<String> listCategory = new ArrayList<String>(); 
   
        for (NameCategory category : Arrays.asList(NameCategory.values())) {
            listCategory.add(String.valueOf(category));   
        }

        return listCategory.contains(newCategory);
        
    }

    // Metodos para peticiones!!!

    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }

    public ArrayList<Book> getAllBooks(){
        return (ArrayList<Book>) bookRepository.findAll();
    }

    public ArrayList<Book> getAllByNameLikeThing(String nombre){
        return bookRepository.findByNameStartingWith(nombre);
    }

    public ArrayList<Book> getAllByCategory(NameCategory category){
        return bookRepository.findByCategory(category);
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

}
