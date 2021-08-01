package com.amazing.books.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false, updatable = true)
    private String name;

    @Column(name="surname", nullable=false, updatable = true)
    private String surname;

    @Column(name="address", nullable=false, updatable = true)
    private String address;

    @Column(name="createdDate", nullable=false, updatable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    /**
     * Relationships!!!
     * 
     */

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    private List<CartClosed> cartClosed = new ArrayList<CartClosed>();

    @OneToOne(mappedBy = "user" ,cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    private CartInProgress cartInProgresses;
    
    
    public User(){
        
    }
   
    public User(Long id, String name, String surname, String address, Date createdDate, List<CartClosed> cartClosed,
            CartInProgress cartInProgresses) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.createdDate = createdDate;
        this.cartClosed = cartClosed;
        this.cartInProgresses = cartInProgresses;
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

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }


    /**
     * @return Date return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * @return ArrayList<CartInProgress> return the cartInProgresses
     */
    public CartInProgress getCartInProgresses() {
        return cartInProgresses;
    }

    /**
     * @param cartInProgresses the cartInProgresses to set
     */
    public void setCartInProgresses(CartInProgress cartInProgresses) {
        this.cartInProgresses = cartInProgresses;
    }


    /**
     * @return ArrayList<CartClosed> return the cartClosed
     */
    public List<CartClosed> getCartClosed() {
        return cartClosed;
    }

    /**
     * @param cartClosed the cartClosed to set
     */
    public void setCartClosed(List<CartClosed> cartClosed) {
        this.cartClosed = cartClosed;
    }


    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

}

