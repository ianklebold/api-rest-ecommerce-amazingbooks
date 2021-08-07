package com.amazing.books.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class LineCart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    //bidireccional
    @JsonBackReference
    @ManyToOne(cascade = {})
    private Cart cart;
    
    //unidireccional
    @OneToOne(cascade = {})
    private Book book;
    
    @Column(name="amount", nullable=false, updatable=true)
    private Integer amount; 


    public LineCart(){

    }


    public LineCart(Long id, Cart cart, Book book, Integer amount) {
        this.id = id;
        this.cart = cart;
        this.book = book;
        this.amount = amount;
    }




    /**
     * @return Cart return the cart
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * @param cart the cart to set
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * @return Book return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return Integer return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
